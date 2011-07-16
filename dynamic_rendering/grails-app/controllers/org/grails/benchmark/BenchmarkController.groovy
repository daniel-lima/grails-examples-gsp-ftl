/* Copyright 2011 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.grails.benchmark

import freemarker.template.Template
import test_freemarker2.Test1

import org.codehaus.groovy.grails.web.util.StreamCharBuffer
import org.codehaus.groovy.grails.web.pages.FastStringWriter

/**
*
* @author <a href='mailto:limcheekin@vobject.com'>Lim Chee Kin</a>
*
* @since 0.1
*/
class BenchmarkController {
	def freemarkerConfig
	def groovyPagesTemplateEngine
	static int TIMES = 5000
        static boolean usePrintln = false
	static String freemarkerTemplateCode = '''\
[#ftl/]
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    [#assign  entityName=g.message({'code': 'test1.label', 'default': 'Test1'}) /]
    <!-- ${entityName} -->
    <title>[@g.message code="default.create.label" args=[entityName] /]</title>                	                    
  </head>
  <body>
    <div class="nav">
      <span class="menuButton"><a class="home" href="${g.createLink({'uri': '/'})}">${g.message({'code':'default.home.label'})}</a></span>
      <span class="menuButton">[@g.link class="list" action="list"][@g.message code="default.list.label" args=[entityName] /][/@g.link]</span>
    </div>
    
    <div class="body">
      <h1>[@g.message code="default.create.label" args=[entityName] /] @counter</h1>
        [#if flash.message?exists]
        <div class="message">${flash.message}</div>
        [/#if]
        [@g.hasErrors bean=test1Instance]
        <div class="errors">
            [@g.renderErrors bean=test1Instance _as="list" /]
        </div>     
        [/@g.hasErrors]
        [@g.form action="save2" ]
        <div class="dialog">
          <table>
            <tbody>
              <tr class="prop">
                <td valign="top" class="name">
                  <label for="prop1">[@g.message code="test1.prop1.label" default="Prop1" /]</label>
                </td>
		[#assign hasErrors][@g.hasErrors bean=test1Instance  field = 'prop1']errors[/@g.hasErrors][/#assign]
                <td valign="top" class="value ${hasErrors}">
                  [@g.textField name="prop1" value="${test1Instance.prop1}" /]
                </td>                       
              </tr>
              
              <tr class="prop">
                <td valign="top" class="name">
                  <label for="prop2">[@g.message code="test1.prop2.label" default="Prop2" /]</label>
                </td>
		[#assign hasErrors][@g.hasErrors bean=test1Instance  field = 'prop2']errors[/@g.hasErrors][/#assign]
                <td valign="top" class="value ${hasErrors}">
                  [@g.textField name="prop2" value=test1Instance.prop2 /]
                </td>                  
              </tr>
              
              <tr class="prop">
                <td valign="top" class="name">
                  <label for="prop3">[@g.message code="test1.prop3.label" default="Prop3" /]</label>
                                </td>
                <td valign="top" class="value">
                  [@g.datePicker name="prop3" precision="day" value=test1Instance.prop3  /]
                </td>
              </tr>
            </tbody>
          </table>	  
	</div>
        <div class="buttons">
          <span class="button">[@g.submitButton name="create" class="save" value="${g.message({'code': 'default.button.create.label', 'default': 'Create'})}" /]</span>
        </div>
        [/@g.form]
    </div>
  </body>
</html>
 '''  
	static String gspTemplateCode = '''\
<%@ page import="test_freemarker2.Test1" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'test1.label', default: 'Test1')}" />
	<!-- ${entityName} -->
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /> @counter</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${test1Instance}">
            <div class="errors">
                <g:renderErrors bean="${test1Instance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="prop1"><g:message code="test1.prop1.label" default="Prop1" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: test1Instance, field: 'prop1', 'errors')}">
                                    <g:textField name="prop1" value="${fieldValue(bean: test1Instance, field: 'prop1')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="prop2"><g:message code="test1.prop2.label" default="Prop2" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: test1Instance, field: 'prop2', 'errors')}">
                                    <g:textField name="prop2" value="${test1Instance?.prop2}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="prop3"><g:message code="test1.prop3.label" default="Prop3" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: test1Instance, field: 'prop3', 'errors')}">
                                    <g:datePicker name="prop3" precision="day" value="${test1Instance?.prop3}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
'''
	def index = { }
	
	def freemarker = {
            FastStringWriter out = new FastStringWriter()
            //StringWriter out = new StringWriter()
		def test1Instance = new Test1(prop2: "This is freemarker test ") 
		long startTime = System.currentTimeMillis()
		for (int i = 0; i < TIMES; i++) {
			test1Instance.prop2 += i
			new Template("freemarkerTemplate${i}",
					new StringReader(freemarkerTemplateCode.replace('@counter', String.valueOf(i))),
					freemarkerConfig.configuration)
					.process([test1Instance: test1Instance, flash:flash], out)
			out.flush(); clearBuffer(out);
			if (usePrintln) {println "Executing... $i"}
		}
		response.setContentType("text/json;charset=UTF-8")
		long timeTaken = System.currentTimeMillis() - startTime
		render '{"message":"' + TIMES + ' times completed in ' + timeTaken + 'ms."}'
	}
	
	def gsp = {
            FastStringWriter out = new FastStringWriter()
            //StringWriter out = new StringWriter()
		def test1Instance = new Test1(prop2: "This is gsp test ") 
		long startTime = System.currentTimeMillis()
		for (int i = 0; i < TIMES; i++) {
			test1Instance.prop2 += i;
			groovyPagesTemplateEngine.createTemplate(gspTemplateCode.replace('@counter', String.valueOf(i)), 
				"gspTemplate${i}").make([test1Instance: test1Instance]).writeTo(out)
			out.flush(); clearBuffer(out);
			if (usePrintln) {println "Executing... $i"}
		}
		response.setContentType("text/json;charset=UTF-8")
		long timeTaken = System.currentTimeMillis() - startTime
		render '{"message":"' + TIMES + ' times completed in ' + timeTaken + 'ms."}'
	}
	
	def freemarkerTemplateCreation = {
		long startTime = System.currentTimeMillis()
		for (int i = 0; i < TIMES; i++) {
			new Template("freemarkerTemplate${i}",
					new StringReader(freemarkerTemplateCode.replace('@counter', String.valueOf(i))),
					freemarkerConfig.configuration)
			if (usePrintln) {println "Executing... $i"}
		}
		response.setContentType("text/json;charset=UTF-8")
		long timeTaken = System.currentTimeMillis() - startTime
		render '{"message":"' + TIMES + ' times completed in ' + timeTaken + 'ms."}'
	}
	
	def gspTemplateCreation = {
		long startTime = System.currentTimeMillis()
		for (int i = 0; i < TIMES; i++) {
			groovyPagesTemplateEngine.createTemplate(gspTemplateCode.replace('@counter', String.valueOf(i)),
				"gspTemplate${i}")
			if (usePrintln) {println "Executing... $i"}
		}
		response.setContentType("text/json;charset=UTF-8")
		long timeTaken = System.currentTimeMillis() - startTime
		render '{"message":"' + TIMES + ' times completed in ' + timeTaken + 'ms."}'
	}
	
	def freemarkerRendering = {
            FastStringWriter out = new FastStringWriter()
            //StringWriter out = new StringWriter()
		def test1Instance = new Test1(prop2: "This is freemarker test ")
		long startTime = System.currentTimeMillis()
		def t=new Template("freemarkerTemplate",
					new StringReader(freemarkerTemplateCode),
					freemarkerConfig.configuration)
		for (int i = 0; i < TIMES; i++) {
			test1Instance.prop2 += i
			t.process([test1Instance: test1Instance, flash:flash], out)
			out.flush(); clearBuffer(out);
			if (usePrintln) {println "Executing... $i"}
		}
		response.setContentType("text/json;charset=UTF-8")
		long timeTaken = System.currentTimeMillis() - startTime
		render '{"message":"' + TIMES + ' times completed in ' + timeTaken + 'ms."}'
	}
   
	def gspRendering = {
            FastStringWriter out = new FastStringWriter()
            //StringWriter out = new StringWriter()
		def test1Instance = new Test1(prop2: "This is gsp test ")
		long startTime = System.currentTimeMillis()
		def t=groovyPagesTemplateEngine.createTemplate(gspTemplateCode, 'gspTemplate')
		for (int i = 0; i < TIMES; i++) {
			test1Instance.prop2 += i
			t.make([test1Instance: test1Instance]).writeTo(out)
			out.flush(); clearBuffer(out);
			if (usePrintln) {println "Executing... $i"}
		}
		response.setContentType("text/json;charset=UTF-8")
		long timeTaken = System.currentTimeMillis() - startTime
		render '{"message":"' + TIMES + ' times completed in ' + timeTaken + 'ms."}'
	}

        def freemarkerPage = {
            FastStringWriter out = new FastStringWriter()
            //StringWriter out = new StringWriter()
            def test1Instance = new Test1(prop2: "This is freemarker test ")             
            new Template("freemarkerTemplate",
                         new StringReader(freemarkerTemplateCode.replace('@counter', '0')),
                         freemarkerConfig.configuration)
            .process([test1Instance: test1Instance, flash:flash], out)
            out.flush(); 
            render out.toString()            
        }

        def gspPage = {
            FastStringWriter out = new FastStringWriter()
            //StringWriter out = new StringWriter()
            def test1Instance = new Test1(prop2: "This is gsp test ") 
            groovyPagesTemplateEngine.createTemplate(gspTemplateCode.replace('@counter', '0'), 
                                                     "gspTemplate").make([test1Instance: test1Instance]).writeTo(out)
            out.flush();
            render out.toString()
        }

        private void clearBuffer(StringWriter w) {
            StringBuffer s = w.buffer
            if (s.length() > 0) {
                s.delete(0, s.length())
            }
        }

        private void clearBuffer(FastStringWriter w) {
            StreamCharBuffer s = w.buffer
            s.reset()
        }
}
