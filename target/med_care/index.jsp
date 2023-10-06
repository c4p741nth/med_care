<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <button id="changeLocationButton">Go to Example.com</button>

    <script>
        // Get a reference to the button element
        var button = document.getElementById("changeLocationButton");

        // Add a click event listener to the button
        button.addEventListener("click", function() {
            // Change the window location to the desired URL
            window.location.href = "https://www.example.com";
        });
    </script>
</body>
</html>