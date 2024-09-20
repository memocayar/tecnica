#language:es

Característica: Login en Wallbit

  Escenario: Iniciar sesión en Wallbit con un usuario registrado
    Dado un usuario que esta en la pagina de crear cuenta y presiona iniciar sesion
    Cuando introduce "braian@test.com" como email
    E introduce "Wallbit.1234" como contrasenia
    Entonces el usuario puede ver la pagina de Descarga de la App

  Escenario: El campo de email está vacío
    Dado un usuario que esta en la pagina de crear cuenta y presiona iniciar sesion
    Cuando introduce " " como email
    Entonces el sistema debe mostrar un mensaje de error que dice "Email is required"

  Escenario: El formato de email es inválido
    Dado un usuario que esta en la pagina de crear cuenta y presiona iniciar sesion
    Cuando introduce "braianinvalido" como email
    Entonces el sistema debe mostrar un mensaje de error que dice "It must be a valid email."

  Escenario: El campo de contraseña está vacío
    Dado un usuario que esta en la pagina de crear cuenta y presiona iniciar sesion
    Cuando introduce "braian@test.com" como email
    Y introduce " " como contrasenia
    Entonces el sistema debe mostrar un mensaje de error que dice "Password is required"

  Escenario: Combinación de email y contraseña incorrectos
    Dado un usuario que esta en la pagina de crear cuenta y presiona iniciar sesion
    Cuando introduce "braian@test.com" como email
    Y introduce "contrasenaIncorrecta" como contrasenia
    Entonces el sistema debe mostrar un mensaje de error que dice "Usuario o contraseña incorrectos."