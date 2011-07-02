[#ftl /]

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        [#assign entityName=g.message({'code': 'trip.label', 'default': 'Trip'}) /]
        <title>[@g.message code="default.edit.label" args=[entityName] /]</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${g.createLink({'uri': '/'})}">[@g.message code="default.home.label"/]</a></span>
            <span class="menuButton">[@g.link class="list" action="list"][@g.message code="default.list.label" args=[entityName] /][/@g.link]</span>
            <span class="menuButton">[@g.link class="create" action="create"][@g.message code="default.new.label" args=[entityName] /][/@g.link]</span>
        </div>
        <div class="body">
            <h1>[@g.message code="default.edit.label" args=[entityName] /]</h1>
            [#if flash.message?exists]
            <div class="message">${flash.message}</div>
            [/#if]
            [@g.hasErrors bean=tripInstance]
            <div class="errors">
                [@g.renderErrors bean=tripInstance _as="list" /]
            </div>
            [/@g.hasErrors]
            [@g.form method="post" ]
                [@g.hiddenField name="id" value=tripInstance.id! /]
                [@g.hiddenField name="version" value=tripInstance.version! /]
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="city">[@g.message code="trip.city.label" default="City" /]</label>
                                </td>
                                <td valign="top" class="value ${g.hasErrors({'bean': tripInstance, 'field': 'city'}, 'errors')}">
                                    [@g.textField name="city" value=tripInstance.city! /]
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="endDate">[@g.message code="trip.endDate.label" default="End Date" /]</label>
                                </td>
                                <td valign="top" class="value ${g.hasErrors({'bean': tripInstance, 'field': 'endDate'}, 'errors')}">
                                    [@g.datePicker name="endDate" precision="day" value=tripInstance.endDate!  /]
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name">[@g.message code="trip.name.label" default="Name" /]</label>
                                </td>
                                <td valign="top" class="value ${g.hasErrors({'bean': tripInstance, 'field': 'name'}, 'errors')}">
                                    [@g.textField name="name" value=tripInstance.name! /]
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="notes">[@g.message code="trip.notes.label" default="Notes" /]</label>
                                </td>
                                <td valign="top" class="value ${g.hasErrors({'bean': tripInstance, 'field': 'notes'}, 'errors')}">
                                    [@g.textField name="notes" value=tripInstance.notes! /]
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="purpose">[@g.message code="trip.purpose.label" default="Purpose" /]</label>
                                </td>
                                <td valign="top" class="value ${g.hasErrors({'bean': tripInstance, 'field': 'purpose'}, 'errors')}">
                                    [@g.textField name="purpose" value=tripInstance.purpose! /]
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="startDate">[@g.message code="trip.startDate.label" default="Start Date" /]</label>
                                </td>
                                <td valign="top" class="value ${g.hasErrors({'bean': tripInstance, 'field': 'startDate'}, 'errors')}">
                                    [@g.datePicker name="startDate" precision="day" value=tripInstance.startDate!  /]
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button">[@g.actionSubmit class="save" action="update" value="${g.message({'code': 'default.button.update.label', 'default': 'Update'})}" /]</span>
                    <span class="button">[@g.actionSubmit class="delete" action="delete" value="${g.message({'code': 'default.button.delete.label', 'default': 'Delete'})}" onclick="return confirm('${g.message({'code': 'default.button.delete.confirm.message', 'default': 'Are you sure?'})}');" /]</span>
                </div>
            [/@g.form]
        </div>
    </body>
</html>
