function showAlert() {
    const url = window.location.href
    if (url.length > 33) {
        let userMessage = url.split("message")[1]

        if (userMessage === "blank") {
            userMessage = "Preencha todos os campos"
        } else {
            userMessage = "E-mail já cadastrado"
        }
        
        alert(userMessage)
    }
    
}

showAlert();