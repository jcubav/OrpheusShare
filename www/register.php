<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<?php session_start();?>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>REGISTER</title>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="container">
    	<!-- header -->
        <div id="logo"><a href="#">ORPHEUS SHARE</a></div>
        <div id="menu">
            <a href="./index.php">home</a>
            <a href="./about_us.php">about us</a>
            <a href="./services.php">services</a>
            <a href="./contacts.php">contacts</a>
        </div>
        <!--end header -->     
        <!-- main -->
        <div id="main">
         	<div id="sidebar">
          		<h2>Lorem Ipsum</h2>
           		<ul>
              			<li><a href="./register.php">Register</a></li>
              			<li><a href="#">Download Player</a></li>
            		</ul>
            	</div>
          	<div id="text">
				<?php include ('./message.php'); ?>	
				<?php
					if ($_SESSION['name']== NULL)
							$_SESSION['name']= "";
							
					if ($_SESSION['nick']== NULL)
						$_SESSION['nick']= "";
					if ($_SESSION['email']== NULL)
						$_SESSION['email']= "";
					if ($_SESSION['contact']== NULL)
						$_SESSION['contact']= "";
						
				?>
			<form name="form1" method="post" action="./doregister_user.php">
			<p>&nbsp; </p>
			<table width="520" border="0">
				<tr> 
				      <td width="270" height="25"><b>Name *</b></td>
				      <td colspan="2" width="400" height="25"><input type="text" name="name" value="<?php print $_SESSION['name'];?>"></td>
				</tr>
				<tr>
					<td width="270" height="25"><b>Please select a password *</b></td>
					<td colspan="2" width="400" height="25"><input type="password" name="pass"></td>
				</tr>
						    
				<tr> 
					<td width="270" height="25"><b>Gender</b></td>
					<td colspan="2" width="400" height="25"> <select name="gender">
				        <option value="Male">Male</option>
				        <option value="Female">Female</option>
				        </select></td>	
			    	</tr>
						   
				<tr> 
				<td width="270" height="25"><b>Contact Number *</b></td>
				<td colspan="2" width="400" height="25"><input type="text" name="contact" value="<?php print $_SESSION['contact'];?>"></td>
				</tr>
				<tr> 
				<td width="270" height="25"><b>Email ID *</b></td>
				<td colspan="2" width="400" height="25"><input type="text" name="email" value="<?php print $_SESSION['email'];?>"></td>
				</tr>  
				<tr>
 					<td width="270" height="30"><div align="right"></div></td>
					<td colspan="2" width="400" height="30"><input type="submit" value="Submit" name="submit"></td>
				</tr>
			</table>
			 
			</form><br><br><br><br><br>		
		</div>
	</div>


<div id="footer">
        <div id="menu_footer">
		<a href="./index.php">home</a> | <a href="#">about</a> | <a href="#">services</a> | </a> | <a href="#">contact</a>
	</div>

        <div id="left_footer">
		&copy; Copyright 2009 <b>www.orpheusshare.com</b>
	</div>
            
	<div id="right_footer">
		Design by <strong>A2J2</strong>
   	</div>
</div>
    <!-- end footer -->
</div>
</body>
</html>
