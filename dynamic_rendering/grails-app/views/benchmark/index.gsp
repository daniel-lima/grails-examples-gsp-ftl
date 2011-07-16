<%--
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
  *
 * @author <a href='mailto:limcheekin@vobject.com'>Lim Chee Kin</a>
 */
 --%>
<html>
<head>
<title>FreeMarker vs. GSP for Dynamic Template Creation, Rendering and Both</title>
<meta name="layout" content="main" />
<g:javascript library="jquery" plugin="jquery" />
<script type="text/javascript">
	$(function() {
		$('table a').click(remoteCall);
	});
	function remoteCall(event) {
		var action = $(event.target).attr('id');
		$('#' + action + 'Result').text('executing...');
		$.ajax({
			url : action,
			success : function(response) {
				$('#' + action + 'Result').text(response.message);

			},
			error : function() {
				$('#' + action + 'Result').text(
						'Unable to connect to remote action: ' + action + '.');
			}
		});
		return false;
	}
</script>
</head>
<body>
<div style="padding: 2em">
<h2>FreeMarker vs. GSP for Dynamic Template Creation, Rendering and Both</h2>
<p>Click the link below to run the rendering benchmark test (It is AJAX
call):
<p>
<table id="benchmark" style="width: 50%">
<tr>
<th>FreeMarker</th><th>GSP</th>
</tr>
<tr>
<td>
	<a id="freemarkerTemplateCreation" href="#">Template Creation</a> <span id="freemarkerTemplateCreationResult"></span><br />
	<a id="freemarkerRendering" href="#">Rendering</a> <span id="freemarkerRenderingResult"></span><br />
	<a id="freemarker" href="#">Both</a> <span id="freemarkerResult"></span>
</td>
<td>
	<a id="gspTemplateCreation" href="#">Template Creation</a> <span id="gspTemplateCreationResult"></span><br />
	<a id="gspRendering" href="#">Rendering</a> <span id="gspRenderingResult"></span><br />
	<a id="gsp" href="#">Both</a> <span id="gspResult"></span>
</td>
</tr>
</table>
<div style="margin-top: 30px; background-color: yellow; border: 1px solid red; width: 50%; padding: 1em">
The objective of this exercise is to evaluate which template engine (FreeMarker or GSP) to use as 
<a href="http://code.google.com/p/grails-form-builder-plugin/wiki/Architecture#Form_Template" target="_blank">Form Template</a> 
of the <a href="http://code.google.com/p/grails-form-builder-plugin" target="_blank">Grails Form Builder Plugin</a>. 
Please let's me know if you have better way to write the code of FreeMarker Template or GSP Template rendering part.</div>
</div>
</body>
</html>
