<?php
class register{


  require_once 'actions.php';
  $response = array("error" => FALSE );

  $action = new actions();

  $inputJson = file_get_contents('php://input');
  $input = json_decode($inputJson,true);
  
  $actionTrigger  = new action();

  $emailExists = $actionTrigger->doesItExist("email",$input['email']);
  if($emailExists){
    $response["error"] = TRUE;
    $response["error_message"] = "ughhhh....did you froget about me or something?\n";
    echo json_encode($response);
  }

  $user_nameExists = $actionsTrigger->doesItExist("user_name",$input['user_name']);
  if($user_nameExists){
    $response["error"] = TRUE;
    $response["error_message"] = "All these people taking up our names!!\n We will make the internet great again!";
    echo json_encode($response);
  }

if($user_nameExists & $emailExists){
  $userCredentials = $actionsTrigger->registerNewUser($input['name'],$input['user_name'],$input['email'],$input['password']);
  if($userCredentials != FALSE){
    $response["error"] = FALSE;
    $response["uid"] = $userInfo["uid"];
    $response["user"]["user_name"] = $userInfo["user_name"];
    $response["user"]["name"] = $userInfo["nameOfUser"];
    $response["user"]["email"] = $userInfo["email"];
    echo json_encode($response);
  }else{
    $response["error"] = TRUE;
    $response["error_message"] = "We messed up X0!";
    echo json_encode($response);
  }
}

}

?>
