function GetXmlHttpObjectShop()
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
            

function getShoppingList() {


    var xmlHttp = GetXmlHttpObjectShop();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {

        //sending selected country to servlet
        var url = "controller_servl?event=SHOPP";
        xmlHttp.onreadystatechange = function(){shoopinglist_return(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}


function shoopinglist_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {


        var text = xmlHttp.responseText;

        document.getElementById("newpage").innerHTML = text;


    }

}



function insertShoppitem() {



    var title = document.forms.formaddshop.titleaddshop.value;
    var quantity = document.forms.formaddshop.quantityaddshop.value;
    var price = document.forms.formaddshop.priceaddshop.value;
    var radios = document.getElementsByName('statusradio');
   
    
    
    if(notnull_validation(title)){
       
        style_inp("titleadditem");
        
    }else{
        style_abstract_valid("titleadditem");
    }
    
    
    if(positive_number_validation(quantity)){
       
        style_inp("quantityadditem");
        
    }else{
        style_abstract_valid("quantityadditem");
    }
    
    if(notnegative_number_validation(price)){
       
        style_inp("priceadditem");
        
    }else{
        style_abstract_valid("priceadditem");
    }
    
    
    
    
    
    
    if(radio_validation(radios)){
       
       for (var i = 0, length = radios.length; i < length; i++) {
               
                    radios[i].required=false;
                    
            }
      
        
    }else{
        for (var i = 0, length = radios.length; i < length; i++) {
               
                    radios[i].required=true;
                    
            }
    }
    
    
    
    
    
    if (radio_validation(radios) && notnull_validation(title) && positive_number_validation(quantity) && notnegative_number_validation(price)) {

        document.getElementById("addshopbutton").setAttribute("data-dismiss", "modal");
       var xmlHttp = GetXmlHttpObjectShop();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request")
            return
        }
        else

        {

            var InvForm = document.forms.formaddshop;
            var SelBranchVal = "";
            var x = 0;
            var count = 0;

            var temp = InvForm.assignedToShop.length;
            for (x = 0; x < InvForm.assignedToShop.length; x++)
            {
                if (InvForm.assignedToShop[x].selected)
                {
                    if (temp == 1) {
                        SelBranchVal = InvForm.assignedToShop[x].value;
                    } else {
                        count = count + 1;
                        if (count == 1) {
                            SelBranchVal = InvForm.assignedToShop[x].value + SelBranchVal;
                        } else {
                            SelBranchVal = InvForm.assignedToShop[x].value + "," + SelBranchVal;
                        }
                    }
                }
            }

            var radios = document.getElementsByName('statusradio');

            for (var i = 0, length = radios.length; i < length; i++) {
                if (radios[i].checked) {
                    status = radios[i].value;
                    break;
                }
            }

           
            var url = "controller_servl?event=InsertShoppItem&title=" + document.getElementById("titleadditem").value
                    + "&quantity=" + document.getElementById("quantityadditem").value + "&price=" + document.getElementById("priceadditem").value
                    + "&status=" + status + "&assignedto=" + SelBranchVal;

            xmlHttp.onreadystatechange = function(){shoopinglist_return(xmlHttp)};

            xmlHttp.open("GET", url, true)
            xmlHttp.send(null)
        }
    } else {
        document.getElementById("addshopbutton").removeAttribute("data-dismiss");
        
         var htmlString = "";  

        htmlString =
                '<div>'+'<div class = "glyphicon glyphicon-remove-circle">'+'</div>'+'  Fill The Red Required Inputs'+'</div>';
        
         document.getElementById("suc_todo_mes_valid_S").style.display="block";
        document.getElementById("suc_todo_mes_valid_S").setAttribute("class","alert alert-danger pull-left alert_messa_danger");

 
        document.getElementById("suc_todo_mes_valid_S").innerHTML=htmlString;
        
        
        
    }




}


function editShopItem(shitemid) {



    var xmlHttp = GetXmlHttpObjectShop();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {

        var url = "controller_servl?event=SHOPDETAILS&itemid=" + shitemid + "&tag=edit";


        xmlHttp.onreadystatechange = function(){editshoopinglist_return(xmlHttp)};
        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)


    }






}

function editshoopinglist_return(xmlHttp) {


    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {


        var text = xmlHttp.responseText;

        document.getElementById("editShop_modal_body").innerHTML = text;


    }



}


function Save_changes_shop(itemID) {

    var title = document.forms.editaskform.titleeditshop.value;
    var quantity = document.forms.editaskform.quantityeditshop.value;
    var price = document.forms.editaskform.priceeditshop.value;
    var radios = document.getElementsByName('statusradio2');
    
    
    if(notnull_validation(title)){
       
        style_inp("titleedititem");
        
    }else{
        style_abstract_valid("titleedititem");
    }
    
    
    if(positive_number_validation(quantity)){
       
        style_inp("quantityedititem");
        
    }else{
        style_abstract_valid("quantityedititem");
    }
    
    if(notnegative_number_validation(price)){
       
        style_inp("priceedititem");
        
    }else{
        style_abstract_valid("priceedititem");
    }
    
    
    
    
    
    
    if(radio_validation(radios)){
       
       for (var i = 0, length = radios.length; i < length; i++) {
               
                    radios[i].required=false;
                    
            }
      
        
    }else{
        for (var i = 0, length = radios.length; i < length; i++) {
               
                    radios[i].required=true;
                    
            }
    }
    
    

    if (notnull_validation(title) && notnegative_number_validation(price) && positive_number_validation(quantity) && radio_validation(radios)) {
        document.getElementById("editshopbutton").setAttribute("data-dismiss", "modal");

       
      var  xmlHttp = GetXmlHttpObjectShop();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        else
        {


            var InvForm = document.forms.editaskform;
            var SelBranchVal = "";
            var x = 0;
            var count = 0;

            var temp = InvForm.assignedToShopedit.length;

            for (x = 0; x < InvForm.assignedToShopedit.length; x++)
            {
                if (InvForm.assignedToShopedit[x].selected)
                {
                    if (temp == 1) {

                        SelBranchVal = InvForm.assignedToShopedit[x].value;
                    } else {

                        count = count + 1;

                        if (count == 1) {
                            SelBranchVal = InvForm.assignedToShopedit[x].value + SelBranchVal;

                        } else {

                            SelBranchVal = InvForm.assignedToShopedit[x].value + "," + SelBranchVal;

                        }


                    }

                    //alert(InvForm.kb[x].value);

                }
            }




            for (var i = 0, length = radios.length; i < length; i++) {
                if (radios[i].checked) {
                    status = radios[i].value;
                    break;
                }
            }


            var url = "controller_servl?event=UPDATESHOP&itemid=" + itemID + "&title=" + document.getElementById("titleedititem").value
                    + "&quantity=" + document.getElementById("quantityedititem").value + "&price=" + document.getElementById("priceedititem").value
                    + "&status=" + status + "&assignedto=" + SelBranchVal;




            xmlHttp.onreadystatechange = function(){shoopinglist_return(xmlHttp)};
            xmlHttp.open("GET", url, true);
            xmlHttp.send(null);


        }
    } else {
        document.getElementById("editshopbutton").removeAttribute("data-dismiss");
        
         var htmlString = "";  

        htmlString =
                '<div>'+'<div class = "glyphicon glyphicon-remove-circle">'+'</div>'+'  Fill The Red Required Inputs'+'</div>';
        
        
        document.getElementById("suc_todo_mes_valid_Sed").style.display="block";
        document.getElementById("suc_todo_mes_valid_Sed").setAttribute("class","alert alert-danger pull-left alert_messa_danger");

        
        document.getElementById("suc_todo_mes_valid_Sed").innerHTML=htmlString;
        
        
        
        
        
    }


}






function deleteShopItem(shitemid) {


    var xmlHttp = GetXmlHttpObjectShop();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {

        var url = "controller_servl?event=SHOPDETAILS&itemid=" + shitemid + "&tag=delete";


        xmlHttp.onreadystatechange = function(){deleteshoppinglist_return(xmlHttp)};
        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)


    }




}


function deleteshoppinglist_return(xmlHttp) {




    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {


        var text = xmlHttp.responseText;

        document.getElementById("deleteShop_modal_body").innerHTML = text;


    }



}


function deleteShopItemItem(shitemid) {




    var xmlHttp = GetXmlHttpObjectShop();

    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {

        var url = "controller_servl?event=DELETESHOP&itemid=" + shitemid;


        xmlHttp.onreadystatechange = function(){shoopinglist_return(xmlHttp)};
        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)


    }



}

var asc_descSHOP = false;

function sorttableshopSHOP(sortedTable) {

    

    var xmlHttp = GetXmlHttpObjectShop();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {

        asc_descSHOP = !asc_descSHOP;

        // alert(asc_desc);

        var url = "controller_servl?event=SHOPSORT&sortedTag=" + sortedTable + "&ssa=" + asc_descSHOP;
        xmlHttp.onreadystatechange = function(){sortedfuncTableSHOP(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)


    }




}


function sortedfuncTableSHOP(xmlHttp) {


    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("changedTableSHOP").innerHTML = text;


    }



}









var multiSelect = {};
function antegeia() {
    var s = document.getElementsByTagName('select');
    for (var i = 0; i < s.length; i++) {
        if (s[i].multiple) {
            var n = s[i].name;
            multiSelect[n] = [];
            for (var j = 0; j < s[i].options.length; j++) {
                multiSelect[n][j] = s[i].options[j].selected;
            }
            s[i].onchange = changeMultiSelect2;
        }
    }
}
function changeMultiSelect() {
    var n = this.name;
    for (var i = 0; i < this.options.length; i++) {
        if (this.options[i].selected) {
            multiSelect[n][i] = !multiSelect[n][i];
        }
        this.options[i].selected = multiSelect[n][i];
    }
}
window.onload = init;