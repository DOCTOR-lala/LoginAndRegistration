<?php

class actions{

  


  public function registerNewUser($name,$user_name,$email,$password){
    $query = mysqli_query("INSERT INTO users(name,user_name,email,password) VALUES ($name,$user_name,$email,$password);");
    if($query)
      return true;
    else
      return false;
  }


  public function doesItExists($field, $value){
    $query = mysqli_query("SELECT $field FROM users WHERE $field = $value;");
    if(mysql_num_rows($query) > 0)
      return true;
    else
      return false;
  }


}



 ?>
