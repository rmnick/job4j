# бот-говорилка на сокетах
1. Создать бот - мудрый Оракл.
 
2. Серверная сторона.
 

Socket socket =  new ServerSocket(port).accept();
PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
do {
    System.out.println("wait command ...");
    String ask = in.readLine();
    System.out.println(ask);
    if ("hello".equals(ask)) {
        out.println("Hello, dear friend, I'm a oracle.");
        out.println();
    } 
} while ("exit".equals(ask));

Сервер должен отвечать на простые вопросы. Если Оралку сказали пока. То приложение выключается.

3. Клиент.

Socket socket = new Socket(InetAddress.getByName(ip), port);
PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
Scanner console = new Scanner(System.in);
do {
    out.println("Hello oracle");
    String str;
    while (!(str = in.readLine()).isEmpty()) {
        System.out.println(str);
    }
} while ()

4. Важно. что Оракл может отправлять большие сообщения. Что бы понять когда конец сообщение он отправляет пустую строку.

5. Обязательно напишите тесты