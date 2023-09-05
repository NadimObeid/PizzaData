document.getElementById("formSubmitBtn").addEventListener("submit", async function(event){
    console.log("event is caught")
    event.preventDefault();
    const name = document.getElementById("Name").nodeValue;
    const type = document.getElementById("Type").nodeValue;
    const price = document.getElementById("Price").nodeValue;
    const weight = document.getElementById("Weight").nodeValue;
    const origin = document.getElementById("Origin").nodeValue;
    const canCauseAllergy = document.getElementById("CanCauseAllergy").nodeValue;
    const typeOfALlergy = document.getElementById("TypeOfAllergy").nodeValue;
    const temperature = document.getElementById("Temperature").nodeValue;
    const expDate = document.getElementById("ExpDate").nodeValue;
    const data = {
        Name: name,
        Type: type,
        Price: price,
        Weight: weight,
        Origin: origin,
        CanCauseAllergy: canCauseAllergy,
        TypeOfALlergy: typeOfALlergy,
        Temperature: temperature,
        expDate: expDate
    };
    console.log(data)
    try{
        const response = await fetch("http://localhost:8080/ingredient/add",{method:"POST",headers:{"Content-Type": "application/json"}, body: JSON.stringify(data)
        });
        const data = await response.json();
        console.log("Ingredient added:", data);
    } catch (error) {
        console.error("Error adding ingredient:", error);
    }
});





