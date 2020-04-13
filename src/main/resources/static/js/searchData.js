function download() {
    var appid= $("#appid").val();
    var pass = $("#pass").val();
    window.location.href="http://localhost:8889/ExportExcel?appid="+appid+"&pass="+pass;
}