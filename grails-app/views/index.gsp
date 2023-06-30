<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>

    <sec:ifLoggedIn>
        <meta http-equiv="refresh" content="0; URL='/dashboard'"/>
    </sec:ifLoggedIn>

    <asset:stylesheet src="index.css"/>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Sora:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
<div class="d-flex align-items-center index-container">
    <div class="col-lg-6 d-flex justify-content-center index-text">
        <div class="col-lg-8">
            <h2 class="display-4 index-title"><span class="gogreen font-weight-bold">Simplifique</span> suas cobranças e melhore o controle financeiro do seu negócio</h2>
            <p class="index-subtitle">Tenha total controle sobre seus recebimentos e otimize o tempo de sua equipe financeira</p>
            <div class="d-flex justify-content-center col-lg-10">
                <a class="btn btn-gogreen btn-lg mt-5 text-decoration-none" href="/user/signUp">Criar conta agora</a>
            </div>
        </div>
    </div>
    <div class="col-lg-6 d-flex justify-content-center index-image-div">
        <asset:image class="index-banner" src="banner.png"/>
    </div>
</div>
</body>
</html>
