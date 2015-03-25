<?php 
if (!empty($_SESSION['msg']))
{
	if ($_SESSION['msg']!="")
	{
		print "<div name='rules' id='rules'><b><font color='red'>&nbsp;<br />".$_SESSION['msg']."</font></b></div>";
		$_SESSION['msg']="";
	}
}
  ?>