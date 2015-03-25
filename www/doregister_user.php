<?php $ebits = ini_get('error_reporting');
error_reporting($ebits ^ E_NOTICE);session_start(); 

$pass = ($_POST['pass']); 
$name = trim($_POST['name']);
$gender = trim($_POST['gender']);
$email = trim($_POST['email']);
$contact = trim($_POST['contact']);
$_SESSION['name']=$name;
$_SESSION['gender']=$gender;
$_SESSION['email']=$email;
$_SESSION['contact']=$contact;
//check if all values have ben entered
if(trim($email)=="" || trim($name)=="" || trim($gender)==""|| trim($contact)=="")
{
	$_SESSION['msg']="<b><font color='red'>&nbsp;Please verify that all entries have been made.</font></b>";
	 header('Location: ./register.php'); 
 die();
 //ob_flush;
}
//connect to db
include('./db.php'); 
$sql = "insert into user values ( NULL, '$email','$name','$pass','$gender');" ;
if(!$res = mysql_query($sql))
 {
  $_SESSION['msg']="Someone has already registered with this email address. Please enter a new address and try again. If this error persits, please contact the system administrator ";
  header('Location: ./register.php'); 
  exit();
 }
header('Location: ./index.php');
print"<P>you have sucessfully registered</P>";
?>