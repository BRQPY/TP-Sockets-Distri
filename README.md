# TP-Sockets-Distri
Trabajo práctico de la materia Sistemas Distribuidos de la carrera de Ing en Informática FPUNA
1.Integrantes:
- Pedro Ramos Genes
- María Jose Grance
- María Jose Melgarejo
- Alexander Vera y Aragón
- Bruno Ruíz Díaz

2.Requerimientos de instalación para modo desarrollador: 
- Registrarse en github.com y Clonar el proyecto (git clone https://github.com/BRQPY/TP-Sockets-Distri)
- Descargar NetBeans IDE o el IDE de su preferencia.
- Descargar los archivos postgresql-42.2.10.jar y java-json.jar
- Instalar JDK 1.8
- Descargar Postgresql PGAdmin4
- Abrir el menú File y submenú Open Project. Seleccionar la carpeta del proyecto "TP-Sockets-Distri". Marcar los proyectos de ChatCliente y ChatServer y seleccionar Open Project.

3. Crear la estructura de la Base de Datos: Utilizando la herramienta PgAdmin4 crear la Base de Datos "call_system" y utilizar el archivo "call_system.sql" para crear las tablas. Las tablas connection_history (contiene el log) y user (contiene el nombre y la contraseña).

4. Compilar y ejecutar los componentes de cada Servidor: Realizar click derecho sobre el proyeto ChatServer y hacer click en la opción "Clean and Build". Realizar click derecho sobre el proyeto ChatServer y hacer click en la opción "Run".

5. Poblar los datos iniciales en la Base de Datos: Luego de compilar y ejecutar el Servidor, se crean los usuarios iniciales para poblar la Base de Datos.

6. Compilar y ejecutar el cliente: Realizar click derecho sobre el proyeto ChatCliente y hacer click en la opción "Clean and Build". Realizar click derecho sobre el proyeto ChatCliente y hacer click en la opción "Run".

7. Documentación de la API y servicios ofrecido por el Servidor:
 * Forma de invocación para Comunicarse y enviar información al Servidor - Data OutputStream:
 - InetAddress:Esta clase representa una dirección de Protocolo de Internet (IP).
  Una dirección IP es un número sin signo de 32 bits o 128 bits utilizado por IP, un protocolo de nivel inferior en el que se crean 			protocolos como UDP y TCP.
  	InetAddress ip_servidor  = InetAddress.getByName("LA IP DEL EQUIPO A CONECTARSE EN FORMATO STRING");

  - int puertoServidorTCP = 1565; //Declaración de un puerto en especifico para la comunicación con el Servidor
  - int puertoServidorUDP = 1566; //Declaración de un puerto en especifico para la comunicación con el Servidor

  - Socket: Esta clase implementa sockets de cliente (también llamados simplemente "sockets"). Un zócalo es un punto final para la 			comunicación entre dos máquinas.
  
  reata un socket de flujo y lo conecta al número de puerto especificado en la dirección IP especificada.
  Si la aplicación ha especificado una fábrica de socket, se llama al método createSocketImpl de esa fábrica para crear la 			implementación del socket real. De lo contrario se crea un socket "plano".

  Parámetros:
  dirección - la dirección IP.
  puerto - el número de puerto.

  	Socket cliente = new Socket(direccion, puerto);
  	
  - Proceso de Envio  del json al servidor
   - Una vez creado la conección con el servidor, se utiliza el metodo sendMessageToServer recibe como parametro el socket del servidor (ServerSocket), el estado de la operacion (String), el tipo de operacion (String - 0: iniciar sesion, 1: ver clientes conectados, 2: iniciar llamada, 3: conversar, 4: terminar llamada, 5: contestar llamada), y el dato (String, dependiendo del tipo de operacion puede ser un JSON o un texto plano)
   
  	-  En este metodo se genera un JSON con los parametros y se crea el objeto "out" instanciando la clase DataOutputStream("OutputStream 				out"), que recibe como parametro el socket creado invocando a su metodo getOutputStream() que retorna un flujo de salida para 				este socket.
  		out = new DataOutputStream(cliente.getOutputStream());

  	-PrintWriter: Imprime representaciones formateadas de objetos en un flujo de salida de texto. Esta clase implementa todos los 				métodos de impresión encontrados en PrintStream. No contiene métodos para escribir bytes sin procesar, para los cuales un 				programa debe usar flujos de bytes no codificados. A diferencia de la clase PrintStream, si se habilita el vaciado automático, 				se realizará solo cuando se invoque uno de los métodos println, printf o format, en lugar de cada vez que salga un carácter de 				nueva línea. Estos métodos utilizan la propia noción de separador de línea de la plataforma en lugar del carácter de nueva línea. Crea un nuevo PrintWriter, sin lavado de línea automático, desde un OutputStream existente. Este práctico constructor crea el 				OutputStreamWriter intermedio necesario, que convertirá los caracteres en bytes utilizando la codificación de caracteres 				predeterminada.
  		Parámetros:
  			out - Un flujo de salida
  
  		PrintWriter pw = new PrintWriter(out);

* Clases y servicios del servidor
 - UserConnected: clase creada para mantener el Array de clientes conectados
        username: nombre del usuario 
        status: estado en que se cuentra al cliente, pueden ser, Libre, Llamando, Llamada entrante, Ocupado
        calling: en caso de que el usuario este llamando es el nombre del usuario al que esta llamando, en caso que sea llamada entrante es el nombre del usuario         que esta llamando, en caso de que sea ocupado es el nombre del usuario con el que se encuentra en una llamada
 - loadUserConnectedList: metodo que recarga carga la lista de usuarios conectados a partir del array de usuarios conectados unlist(ArrayList<UserConnected>)
 - loadConnectionHistoryList: metodo que recarga el historial de conexiones al servidor, consultando a la base de datos
 - getPositionOfClient: metodo que recibe el nombre de un usuario (String) y retorna su posicion en el array de clientes conectados
 - HandleClientTCP: clase del servidor TCP,  acepta las solicitudes e instancia un hilo ProcessClient para escuchar las peticiones del cliente al que acepto
 - ProcessClient: escucha los mensajes TCP de los clientes conectados
 - removeClient: metodo que recibe la posicion de un usuario conecta y lo elimina de la lista de usuarios conectados
 - answerCall: recibe el nombre del usuario que comenzo la llamada(String), el usuario al que se esta llamando(String), el socket del usuario que                   comenzo la llamada(Socket) y el socket del usuario al que se esta llamando(Socket)
 - endCall: recibe el nombre del usuario que comenzo la llamada(String), el usuario al que se esta llamando(String), el socket del usuario que                       comenzo la llamada(Socket) y el socket del usuario al que se esta llamando(Socket) y con que codigo se termina la llamada(String - 0: llamada                     finalizada por uno de los usuarios, 1: llamada rechada por el usuario destino, 2: llamada no contestada por el usuario destino, 3: llamada finalizada             por el servidor)
- sendMessageToClient: metodo que envia un mensaje a un cliente, recibe como parametros el socket del cliente (Socket), el estado de la operacion (String), el tipo de operacion (String - 0: iniciar sesion, 1: ver clientes conectados, 2: iniciar llamada, 3: conversar, 4: terminar llamada, 5: contestar llamada), y el dato (String, dependiendo del tipo de operacion puede ser un JSON o un texto plano)
- loginUser: metodo recibe el nombre del usuario (String) y su contraseña (String) y verifica si el usuario existe en la base de datos con esa contraseña
- initCall: metodo que recibe el socket del usuario destino (Socket), el nombre del usuario que llama (String) y el nombre del usuario al que se llama (String)
- sendMessageUserToUser: metodo que envia un mensaje escrito por un usuario a otro usuario, recibe el socket del usuario que envia(Socket), el socket del usuario que debe recibir el mensaje (Socket), el nombre del usuario que envia (String), el nombre del usuario que recibe (String)
 - HandleClientUDP: clase del servidor UDP, recibe y envia datagramas por protocolo UDP
 - sendDatagramToUser: metodo que recibe la direccion IP(InetAdress) y el puerto(int) del usuario al que se le debe enviar un datagrama, el tipo de operacion (String), y el dato a enviarse (String)
