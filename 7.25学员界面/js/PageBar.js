//table分页        
var pageSize = 5;  //每页显示的记录条数   
var curPage = 0;   //显示第curPage页
var len;         //总行数
var page;        //总页数
$(function () {

    len = $("#show_tab tr").length;   //去掉表头     
    page = len % pageSize == 0 ? len / pageSize : Math.floor(len / pageSize) + 1; //根据记录条数，计算页数
    console.log("len:" + len + "page:" + page);
    //document.getElementById("page").value = page;
    curPage = 1;
    displayPage(); //显示第一页
    ShowPageNum(page);
    $("#nextpage").click(function () {//下一页
        if (curPage < page) {

            curPage ++;

            $(".pages a").removeClass("current");
            $("#page" + curPage).addClass("current");
        }
        else {
            alert("已经到最后一页了");
        }
        displayPage();
    });
    $("#lastpage").click(function () {//上一页
        if (curPage > 1) {
            curPage -- ;
            $(".pages a").removeClass("current");
            $("#page" + curPage).addClass("current");
        }
        else {
            alert("已经到第一页了");
        }
        displayPage();
    });

    $("#page" + curPage).addClass("current");

});
//页面跳转
function PageJump(PageNum) {
    if (PageNum > page || PageNum < 1) {
        alert("页面超出范围");
    }
    else {
        curPage = PageNum;
        $(".pages a").removeClass("current");
        $("#page"+curPage).addClass("current");
    }
    displayPage();
}

function ShowPageNum(pageCount) {

    var PageContent = "";
    PageContent += "<span><a id=\"lastpage\">上一页 </a></span>"
    for (var i = 1; i < pageCount + 1; i++) {

        PageContent += "<span><a href=\"javascript:PageJump('" + i + "')\" id=\"page"+i+"\">" + i + "</a></span>";

    }
    PageContent += "<span class=\"next\"><a id=\"nextpage\">下一页</a></span>"
    $(".pages").html(PageContent);
}




function displayPage() {
    var begin = (curPage - 1) * pageSize; //起始记录号
    var end = begin + pageSize;
    console.log("  begin:" + len + "   end:" + end);
    if (end > len) end = len;
    $("#show_tab tr").hide();
    $("#show_tab tr").each(function (i) {
        if (i >= begin && i <= end)//显示第page页的记录
        {
            $("#show_tab_one").show();
            $(this).show();
            //document.getElementById("curPage").value = curPage;

        }
    });

}
function pageSize2() {
    curPage = 0;   //显示第curPage页   
    pageSize = parseInt(document.getElementById("pageSize").value);
    len = $("#show_tab tr").length - 1;   //去掉表头     
    page = len % pageSize == 0 ? len / pageSize : Math.floor(len / pageSize) + 1; //根据记录条数，计算页数
    console.log("len:" + len + "   page:" + page);
    document.getElementById("page").value = page;
    curPage = 1;
    displayPage(); //显示第一页   
}