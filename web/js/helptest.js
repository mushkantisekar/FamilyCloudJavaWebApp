
function GetXmlHttpObjectHelp()
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


function ajaxslider(tagg) {



    
   

       var xmlHttp = GetXmlHttpObjectHelp();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request")
            return
        }
        else
        {

            var url = "controller_servl?event=HELPCAROUSEL&tag="+tagg;

            xmlHttp.onreadystatechange = function () {
                slid_return(xmlHttp)
            };

            xmlHttp.open("GET", url, true)
            xmlHttp.send(null)


        }



    


}


function slid_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {


        var text = xmlHttp.responseText;

        document.getElementById("myCarousel2").innerHTML = text;


    }

}

