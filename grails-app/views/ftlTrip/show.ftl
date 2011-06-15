[#ftl /]

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        [#assign entityName=g.message({'code': 'trip.label', 'default': 'Trip'}) /]
        <title>[@g.message code="default.show.label" args=[entityName] /]</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${g.createLink({'uri': '/'})}">[@g.message code="default.home.label"/]</a></span>
            <span class="menuButton">[@g.link class="list" action="list"][@g.message code="default.list.label" args=[entityName] /][/@g.link]</span>
            <span class="menuButton">[@g.link class="create" action="create"][@g.message code="default.new.label" args=[entityName] /][/@g.link]</span>
        </div>
        <div class="body">
            <h1>[@g.message code="default.show.label" args=[entityName] /]</h1>
            [#if flash.message?exists]
            <div class="message">${flash.message}</div>
            [/#if]
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name">[@g.message code="trip.id.label" default="Id" /]</td>
                            
                            <td valign="top" class="value">[@g.fieldValue bean=tripInstance field="id" /]</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">[@g.message code="trip.city.label" default="City" /]</td>
                            
                            <td valign="top" class="value">${g.fieldValue({'bean': tripInstance, 'field': "city"})}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">[@g.message code="trip.endDate.label" default="End Date" /]</td>
                            
                            <td valign="top" class="value">[@g.formatDate date=tripInstance.endDate! /]</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">[@g.message code="trip.name.label" default="Name" /]</td>
                            
                            <td valign="top" class="value">${g.fieldValue({'bean': tripInstance, 'field': "name"})}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">[@g.message code="trip.notes.label" default="Notes" /]</td>
                            
                            <td valign="top" class="value">${g.fieldValue({'bean': tripInstance, 'field': "notes"})}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">[@g.message code="trip.purpose.label" default="Purpose" /]</td>
                            
                            <td valign="top" class="value">${g.fieldValue({'bean': tripInstance, 'field': "purpose"})}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">[@g.message code="trip.startDate.label" default="Start Date" /]</td>
                            
                            <td valign="top" class="value">[@g.formatDate date=tripInstance.startDate! /]</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                [@g.form]
                    [@g.hiddenField name="id" value="${tripInstance.id!}" /]
                    <span class="button">[@g.actionSubmit class="edit" action="edit" value="${g.message({'code': 'default.button.edit.label', 'default': 'Edit'})}" /]</span>
                    <span class="button">[@g.actionSubmit class="delete" action="delete" value="${g.message({'code': 'default.button.delete.label', 'default': 'Delete'})}" onclick="return confirm('${g.message({'code': 'default.button.delete.confirm.message', 'default': 'Are you sure?'})}');" /]</span>
                [/@g.form]
            </div>
        </div>
    </body>
</html>
