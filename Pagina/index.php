<!--esta pagina tecnicamente es el log-in, pero se llama index para que la muestre por default-->
<?php
include("loginTester.php");
session_start();

if($_SERVER["REQUEST_METHOD"] == "POST") {
    // username and password sent from form

    $myusername = mysqli_real_escape_string($db,$_POST['username']);
    $mypassword = mysqli_real_escape_string($db,$_POST['password']);

    //aca iria un query para determinar que el carnet y la contrasena sean correctas, por el momento no es funcional
    $sql = "SELECT id FROM admin WHERE username = '$myusername' and passcode = '$mypassword'";
    $result = mysqli_query($db,$sql);
    $row = mysqli_fetch_array($result,MYSQLI_ASSOC);
    $active = $row['active'];

    $count = mysqli_num_rows($result);

    // If result matched $myusername and $mypassword, table row must be 1 row

    if($count == 1) {
        session_register("myusername");
        $_SESSION['login_user'] = $myusername;

        header("location: welcome.php");
    }else {
        $error = "Your Login Name or Password is invalid";
    }
}
?>


<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sistema de cobros Uvg</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="css/freelancer.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">


</head>

<body id="page-top" class="index">

<!-- Navigation -->
<nav id="mainNav" class="navbar navbar-default navbar-fixed-top navbar-custom">
    <div class="container">
        <div class="navbar-header page-scroll">
            <a class="navbar-brand" href="#page-top">CobrosUVG</a>
        </div>
    </div>
</nav>

<!-- Header -->
<header>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="row">
                    <img class="img-responsive" src="img/portfolio/uvg.png" alt="">
                </div>
                <div class="intro-text">
                    <span class="name">Sistema de cobros UVG</span>
                    <span class="skills">Que vas a comer hoy?</span>
                </div>
            </div>
        </div>
    </div>
</header>
<section>
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-lg-offset-4">
                <form id="signin" role="form" action="index.html" method="get">
                    <div class="input-group" id="containerCrn">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input id="carnet" type="number" class="form-control" name="email" value="" placeholder="carnet UVG">
                    </div>
                    <br>
                    <div class="input-group" id="containerPss">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input id="password" type="password" class="form-control" name="password" value="" placeholder="Contrase;a">
                    </div>
                    <button  class="btn btn-primary center-block" onClick='enviar()'>Login</button>
                </form>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<footer class="text-center">
    <div class="footer-above">
        <div class="container">
            <div class="row">

                <div class="footer-col col-md-4">
                    <h3>Acerca del equipo:</h3>
                    <h4>Integrantes: </h4>
                    </br>
                    <div class="text-left">
                        <ul>
                            <li><h5>Pablo Rene Arellano</h5></li>
                            <li><h5>Jose Rodrigo Corona</h5></li>
                            <li><h5>Fernando Gabriel Mendez</h5></li>
                            <li><h5>Juan Pablo Mora</h5></li>
                            <li><h5>Byron Andres Mota</h5></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-below">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    SistemaCobrosUVG &copy; POO 2016
                </div>
            </div>
        </div>
    </div>
</footer>

    <!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="js/jqBootstrapValidation.js"></script>
    <script src="js/contact_me.js"></script>

    <!-- Theme JavaScript -->
    <script src="js/freelancer.js"></script>

</body>

</html>
