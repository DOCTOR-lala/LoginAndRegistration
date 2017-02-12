<?php

class Actions {
  private $connection;
  private $response;

  function __construct(){
    require_once 'Configuration.php';
    $this->connection = new mysqli(host,db_user,db_password,db_name);
    if(mysqli_connect_errno()){
      $this->response['error'] = TRUE;
      $this->response['message'] = "Something went horribly wrong!!!";
      echo json_encode($this->response);
    }
  }

  function isExisting($field,$value){
    $result = $this->connection->prepare("SELECT $field FROM users WHERE $field = ? ");
    $result->bind_param('s' , $value);
    $result->execute();
    $result->store_result();
    if($result->num_rows > 0){
      $result->close();
      return true;
    }
    $result->close();
    return false;
  }

  function getByCredentials($user_name,$password){
    $result = $this->connection->prepare("SELECT * FROM users WHERE user_name = '$user_name' AND password = ? ");
    $result->bind_param('s' , $password);
    #$result->bind_param(1, $email);
    #$result->bind_param(2, $password);
    if($result->execute()){
      $information = $result->get_result()->fetch_assoc();
      $result->close();
      return $information;
    }else return NULL;
  }

  function storeValues($name,$user_name,$email,$password){
    $uniqueID = md5($user_name.$email);
    $result = $this->connection->prepare("INSERT INTO users(uid,nameOfUser, user_name, email, password) VALUES(?,?,?,?,?)");
    $result->bind_param('sssss',$uniqueID,$name,$user_name,$email,$password);
    $result->execute();
    if($this->connection->affected_rows > 0){
        $result->close();
        return true;
    }else {
        $result->close();
        return false;
    }

  }

  function setLoggedIn($bool, $id){
    $result= $this->connection->prepare("UPDATE users set loggedIn = '$bool' where uid = ?");
    $result->bind_param('s', $id);
    $result->execute();
    if($this->connection->affected_rows > 0){
        $result->close();
        return true;
    }else {
        $result->close();
        return false;
    }
  }

  function isLoggedIn($user_name){
    $result = $this->connection->prepare("SELECT loggedIn from users where user_name  = ?");
    $result->bind_param('s', $user_name);
    $result->execute();
    $result->bind_result($bool);
    $result->fetch();
    $result->close();
    return $bool;
  }



}

 ?>
