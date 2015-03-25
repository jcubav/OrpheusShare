<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<?PHP session_start(); 
//connect to db
include('./db.php');
?>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Orpheus Share</title>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="container">
    	<!-- header -->
        <div id="logo"><a href="./index.php">ORPHEUS SHARE</a></div>
        <div id="menu">
            <a href="./index.php">home</a>
            <a href="./about_us.php">about us</a>
            <a href="./contacts.php">contacts</a>
        </div>
        <!--end header -->     
        <!-- main -->
        <div id="main">
          <div id="sidebar">
          	<h2>Lorem Ipsum</h2>
            <ul>
              <li><a href="./register.php">Register</a></li>
              <li><a href="./orpheus.msi">Download Player</a></li>
            </ul>
            </div>
          <div id="text">
			<h1>ORPHEUS SHARE WAS DEVELOPED BY</h1>
			<ul>
			<li>AJITH KUMAR V
			<li>ALAN KOSHY JOHN	
			<li>JACOB ANTONY
			<li>JACOB POULOSE
			</ul>
			<br>
			<br>
	</div>
    </div>
    <!-- end main -->
    <!-- footer -->
    <div id="footer">
            <div id="menu_footer"><a href="./index.php">home</a> | <a href="#">about</a> | <a href="#">services</a> | </a> | <a href="#">contact</a></div>
            <div id="left_footer">&copy; Copyright 2009 <b>www.orpheusshare.com</b></div>
            <div id="right_footer">
Design by <strong>A2J2</strong>

   		</div>
	</div>
    <!-- end footer -->
</div>
</body>
</html>
