<html>
<body>
<h2>web2</h2>
<!-- 模拟单点同时退出 -->
<a id="id_a" href="http://localhost:8380/web1/test?type=redirect&target_url=" target="myFrameName">call web1</a> 
<iframe id="myFrameId" name="myFrameName" scrolling="no" frameborder="0" style="width:0px; height:0px; "></iframe>

<script type="text/javascript">
document.getElementById("id_a").click();
</script>
</body>
</html>
