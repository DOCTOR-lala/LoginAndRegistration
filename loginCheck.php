<?php

class loginCheck{

  require_once 'actions.php';
  $response = array("error" => FALSE );

  $action = new actions();

  $inputJson = file_get_contents('php://input');
  $input = json_decode($inputJson,true);


  $actionTrigger  = new action();

  $userExists = $actionTrigger->doesItExist("user_name",$input[]);

  $userInfo =  $actionTrigger->retriveUser($input['name'] , $input['password']);

  if($userInfo != FALSE){
    $response["error"] = FALSE;
    $response["uid"] = $userInfo["uid"];
    $response["user"]["user_name"] = $userInfo["user_name"];
    $response["user"]["name"] = $userInfo["nameOfUser"];
    $response["user"]["email"] = $userInfo["email"];
    echo json_encode($response);
  }else {
    $response["error"] = TRUE;
    $response["error_message"] = "dem bitches showin fake fake love to me!!!!";
    echo json_encode($response);
  }



}

 ?>
