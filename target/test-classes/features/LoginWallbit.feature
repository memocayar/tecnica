#language:es

Característica: Login en Wallbit

  Escenario: Iniciar sesión en Wallbit con un usuario registrado
    Dado un usuario se encuentra en sign in y presiona log in
    Cuando introduce "braian@test.com" como email
    E introduce "Wallbit.1234" como contrasenia
    Entonces el usuario puede ver la pagina de Descarga de la App

  Escenario: El campo de email esta vacío
    Dado un usuario se encuentra en sign in y presiona log in
    Cuando introduce " " como email
    Y presiona otro lugar de la pantalla
    Entonces el sistema debe mostrar un mensaje de error que dice "Email is required"

  Escenario: El formato de email es inválido
    Dado un usuario se encuentra en sign in y presiona log in
    Cuando introduce "braianinvalido" como email
    Y presiona otro lugar de la pantalla
    Entonces el sistema debe mostrar un mensaje de error que dice "It must be a valid email"

  Escenario: El campo de contraseña esta vacío
    Dado un usuario se encuentra en sign in y presiona log in
    Cuando introduce "braian@test.com" como email
    Y introduce "" como contrasenia
    Entonces el sistema debe mostrar un mensaje de error que dice "Please enter your password"

  Escenario: Combinacion de email y contraseña incorrectos
    Dado un usuario se encuentra en sign in y presiona log in
    Cuando introduce "braian@test.com" como email
    Y introduce "contrasenaIncorrecta" como contrasenia
    Entonces el sistema muestra un mensaje de error
