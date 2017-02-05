<?php
  class connect{
    private $con;

    public function connectTHEdb(){
      require_once'config.php';
      $con -> new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);
      return $this->con;
    }
  }

 ?>
