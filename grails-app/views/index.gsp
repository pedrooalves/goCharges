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
    <div class="col-6 d-flex justify-content-center">
        <div class="col-8">
            <h2 class="display-4 index-title"><span class="gogreen font-weight-bold">Simplifique</span> suas cobranças e melhore o controle financeiro do seu negócio</h2>
        </div>
    </div>
    <div class="col-6 d-flex justify-content-center">
        <asset:image src="banner.png"/>
    </div>
</div>
</body>
</html>
