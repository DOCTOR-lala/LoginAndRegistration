<?php

class actions{


  public function registerNewUser($conobj,$name,$user_name,$email,$password){

    $query = mysqli_query($conobj,"INSERT INTO users(nameOfUser,user_name,email,user_password,) VALUES ($name,$user_name,$email,$password);");
    if($query){
      $success_query = mysqli_query($conobj,"SELECT * FROM users WHERE user_name = $user_name;");
      return $success_query;
    }
      //maybe check if user has been registered by doesItExist
    else
      return false;
  }

//to check if email or uname exist
  public function doesItExist($conobj,$field, $value){
    $query = mysqli_query($conobj,"SELECT $field FROM users WHERE $field = $value;");
    if(mysql_num_rows($query) > 0)
      return true;
    else
      return false;
  }


//in login before calling retrive call doesItExist with user_name, maybe lets see
  public function retriveUser($conobj,$user_name,$password){
    $query = mysqli_query($conobj,"SELECT * FROM users WHERE user_name = $user_name AND user_password = $password);");
    if(mysql_num_rows($query)==1){
      return $query;
    }
    else {
      return false
      //password incorrect
    }
  }



}



 ?>
