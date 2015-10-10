<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${SortedfamToDo}" var="sftd" varStatus="loopStatus">
    <tr>

        <td data-title="AI">${loopStatus.index+1}</td>
        <td data-title="Title">${sftd.title}</td>                                    
        <td data-title="Assigned To">${sftd.assignedTo}</td> 
        <td data-title="Created By">${sftd.createdBy}</td> 
        <td data-title="Due Date" >${sftd.dueDate}</td> 
        <td data-title="Date Completed">${sftd.completedDate}</td>
        <td  data-title="status">${sftd.status}</td>

        <c:choose>
                                            
                                            <c:when test="${(sftd.createdBy eq cur.username)||(cur.director eq 'Y')}">
                                                    
                                                <td><button class="btn btn-default"href="#" data-toggle="modal" data-target="#edittask" value="${sftd.itemID}" onclick="editToDoItem(this.value)"><span class="glyphicon glyphicon-pencil"> Edit</span></button></td>
                                                <td><button class="btn btn-default" href="#" data-toggle="modal" data-target="#deletemodal" value="${sftd.itemID}" onclick="deleteToDoItem(this.value)"><span class="glyphicon glyphicon-remove"> Delete</span></button></td>
                                            </c:when>
                                            
                                            
                                            
                                            <c:otherwise>
                                                
                                                <td><button class="btn btn-default disabled " href="#" data-toggle="modal" data-target="#edittask" value="${sftd.itemID}" onclick="editToDoItem(this.value)"><span class="glyphicon glyphicon-pencil"> Edit</span></button></td>
                                                <td><button class="btn btn-default disabled " href="#" data-toggle="modal" data-target="#deletemodal" value="${sftd.itemID}" onclick="deleteToDoItem(this.value)"><span class="glyphicon glyphicon-remove"> Delete</span></button></td>
                                            </c:otherwise>
                                            
                                            
                                        </c:choose>


    </tr>
</c:forEach>