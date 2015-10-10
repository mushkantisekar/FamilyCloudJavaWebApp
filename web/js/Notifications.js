function GetXmlHttpObjectNotif()
            {
                //creating xmlhttprequestobject.common method for any ajax application
                var xmlHttp = null;
                try
                {
                    // Firefox, Opera 8.0+, Safari
                    xmlHttp = new XMLHttpRequest();
                }
                catch (e)
                {
                    //Internet Explorer
                    try
                    {
                        xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
                    }
                    catch (e)
                    {
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                }
                return xmlHttp;
            }
            
            
function getNotifications()
{

    var xmlHttp=GetXmlHttpObjectNotif();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=NOTIFICATIONS&tag=notifications";
        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){notifications_return(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }

}
function notifications_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("dropdown_notifications").innerHTML = text;
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);
    }
}

function getMessageNotifications()
{

   var xmlHttp=GetXmlHttpObjectNotif();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        
        //sending selected country to servlet
        var url = "controller_servl?event=NOTIFICATIONS&tag=messages";
        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){notificationsMessage_return(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }

}

function notificationsMessage_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);
        
        
        
       
        
        document.getElementById("dropdown_messages").innerHTML = text;
        
    }

}

function getUnreadNotificationsCount(){
    var xmlHttp1=GetXmlHttpObjectNotif();
    if (xmlHttp1 == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        
        //sending selected country to servlet
        var url = "controller_servl?event=GETUNREADCOUNT&tag=notifications";
        //creating callback method.here countrychanged is callback method
        xmlHttp1.onreadystatechange = function(){unread_counter_return(xmlHttp1)};

        xmlHttp1.open("GET", url, true)
        xmlHttp1.send(null)
    }
}
mem=0;
mem2=0;
function unread_counter_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//      var json = JSON.parse(xmlHttp.responseText);
        
        var text = xmlHttp.responseText;
        if(text>mem){
            document.getElementById("notifanime").setAttribute("class","dropdown-toggle rotate_notif");
        }else{
            document.getElementById("notifanime").removeAttribute("class");
        }

        mem=xmlHttp.responseText;
        document.getElementById("badge_counter").innerHTML = text;
        
        
        
        
        setTimeout(function(){getUnreadNotificationsCount();},10000);
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);
    }
}

function getUnreadMessageNotificationsCount(){
    var xmlHttp2=GetXmlHttpObjectNotif();
    if (xmlHttp2 == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        
        //sending selected country to servlet
        var url = "controller_servl?event=GETUNREADCOUNT&tag=messages";
        //creating callback method.here countrychanged is callback method
        xmlHttp2.onreadystatechange = function(){unreadMessage_counter_return(xmlHttp2)};

        xmlHttp2.open("GET", url, true)
        xmlHttp2.send(null)
    }
}

function unreadMessage_counter_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;
        
         if(text>mem2){ 
            document.getElementById("messageanime").setAttribute("class","dropdown-toggle rotate_notif");
            
        }else{
            document.getElementById("messageanime").removeAttribute("class");
            //document.getElementById("messageanime").setAttribute("class","dropdown-toggle");
        }
        mem2=xmlHttp.responseText;
        
        document.getElementById("badge_counter_msg").innerHTML = text;
        setTimeout(function(){getUnreadMessageNotificationsCount();},10000);
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);
    }
}

function showSelectedNotification(notifID){
    var xmlHttp=GetXmlHttpObjectNotif();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
      
        //sending selected country to servlet
        var url = "controller_servl?event=SHOWNOTIFICATION&id="+notifID;
        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){notifications_return_after_select(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}

function notifications_return_after_select(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("dropdown_notifications").innerHTML = text;
        getUnreadNotificationsCount();
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }

}

function redirectTo(type,target){
    if((type=="famcalevents") || (type=="famcalevents_update") || (type=="famcalevents_delete")){
       
        getFamilyCalendar();
    }else if((type==="todo_list") || (type==="todo_list_update") || (type==="todo_list_delete")){
        
        getToDoList();
    }else if((type==="shopping_list") || (type==="shopping_list_update") || (type==="shopping_list_delete")){
        
        getShoppingList();
    }else if(type==="wall_post"){
        
        getWallPost();
    }else if(type==="messages"){
        
        
        show_chatbox('chat'+target);
        RefreshMessage(target);
    }
}