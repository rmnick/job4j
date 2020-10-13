#GC
1. Демонстрация работы GC. Понимание сколько памяти занимает пустой объект. 

2. Работа с VM, задание памяти через ключи xmx, xms.

3. Работа с внешними утилитами для VM, отслеживание работы GC, областей памяти.

4. Реализации кеша на SoftReference. Кеш должен быть абстрактный. 
То есть необходимо, что бы можно было задать ключ получения объекта кеша и в случае если его нет в памяти, задать поведение загрузки этого объекта в кеш. 
Создать программу эмулирующее поведение данного кеша. Программа должна считывать текстовые файлы из системы и выдавать текст при запросе имени файла. Если в кеше файла нет. Кеш должен загрузить себе данные. По умолчанию в кеше нет ни одного файла. Текстовые файл должны лежать в одной директории. Пример. Names.txt, Address.txt - файлы в системе. При запросе по ключу Names.txt - кеш должен вернуть содержимое файла Names.txt.