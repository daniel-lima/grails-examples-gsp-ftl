[#ftl /]

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        [#assign entityName=g.message({'code': 'trip.label', 'default': 'Trip'}) /]
        <title>[@g.message code="default.list.label" args=[entityName] /]</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${g.createLink({'uri': '/'})}">[@g.message code="default.home.label" /]</a></span>
            <span class="menuButton">[@g.link class="create" action="create"][@g.message code="default.new.label" args=[entityName] /][/@g.link]</span>
        </div>
        <div class="body">
            <h1>[@g.message code="default.list.label" args=[entityName] /]</h1>
            [#if flash.message?exists]
            <div class="message">${flash.message}</div>
            [/#if]
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            [@g.sortableColumn property="id" title="${g.message({'code': 'trip.id.label', 'default': 'Id'})}" /]
                        
                            [@g.sortableColumn property="city" title="${g.message({'code': 'trip.city.label', 'default': 'City'})}" /]
                        
                            [@g.sortableColumn property="endDate" title="${g.message({'code': 'trip.endDate.label', 'default': 'End Date'})}" /]
                        
                            [@g.sortableColumn property="name" title="${g.message({'code': 'trip.name.label', 'default': 'Name'})}" /]
                        
                            [@g.sortableColumn property="notes" title="${g.message({'code': 'trip.notes.label', 'default': 'Notes'})}" /]
                        
                            [@g.sortableColumn property="purpose" title="${g.message({'code': 'trip.purpose.label', 'default': 'Purpose'})}" /]
                        
                        </tr>
                    </thead>
                    <tbody>[#function zebra index][#if (index % 2) == 0][#return 'odd' /][#else][#return 'even' /][/#if][/#function]
                    [#list tripInstanceList as tripInstance]
                        <tr class="${zebra(tripInstance_index)}">
                        
                            <td>[@g.link action="show" id=tripInstance.id][@g.fieldValue bean=tripInstance field='id'/][/@g.link]</td>
                        
                            <td>[@g.fieldValue bean=tripInstance field='city'/]</td>
                        
                            <td>[@g.formatDate date=tripInstance.endDate /]</td>
                        
                            <td>[@g.fieldValue bean=tripInstance field='name'/]</td>
                        
                            <td>[@g.fieldValue bean=tripInstance field='notes'/]</td>
                        
                            <td>${g.fieldValue({'bean':tripInstance, 'field': 'purpose'})}</td>
                        
                        </tr>
                    [/#list]
                    </tbody>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                [@g.paginate total=tripInstanceTotal /]
            </div>
        </div>
    </body>
</html>
