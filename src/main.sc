require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Привет!Я твой диджей! || html = "Привет!Я твой диджей!", htmlEnabled = true
        image: https://st.depositphotos.com/1026550/4167/i/450/depositphotos_41679805-stock-photo-dj-silhouette.jpg
        go!: /Выбрать музыку

    state: Hello
        intent!: /привет
        random: 
            a: Здравствуй, мой друг! || htmlEnabled = false, html = "Здравствуй, мой друг!"
            a: Приветики! || htmlEnabled = false, html = "Приветики!"
            a: Хаюшки! || htmlEnabled = false, html = "Хаюшки!"

    state: Bye
        intent!: /пока
        a: Рад был пообщаться! || html = "Рад был пообщаться!"
        random: 
            a: Пока пока! || htmlEnabled = false, html = "Пока пока!"
            a: Еще увидимся! || htmlEnabled = false, html = "Еще увидимся!"
            a: Приходи еще! || htmlEnabled = false, html = "Приходи еще!"

    state: NoMatch
        event!: noMatch
        random: 
            a: Хм, кажется я не понимаю тебя || htmlEnabled = false, html = "Хм, кажется я не понимаю тебя"
            a: Непонятный запросик || htmlEnabled = false, html = "Непонятный запросик"
            a: Давай попробуем еще раз || htmlEnabled = false, html = "Давай попробуем еще раз"

    state: Выбрать музыку
        intent!: /Трек
        random: 
            a: Выбирай какой трек ты хочешь послушать || htmlEnabled = false, html = "Выбирай какой трек ты хочешь послушать"
            a: Готов играть твою любимую музыку || htmlEnabled = false, html = "Готов играть твою любимую музыку"
            a: Есть идеи, что хочется полушать сегодня? || htmlEnabled = false, html = "Есть идеи, что хочется полушать сегодня?"
        buttons:
            "Jazz " -> /Jaz
            "Rock" -> /Rock
            "Pop" -> /NewState_8
        intent: /Трек || onlyThisState = false, toState = "./"

    state: Rock
        audio: https://248305.selcdn.ru/zfl_prod/251178517/251178516/audio/undad7cMU6hhHjO7.mp3?channels={"incompatible":["OUTGOING_CALLS","ALEXA","GOOGLE_ASSISTANCE"],"compatible":["FACEBOOK","WHATSAPP","AIMYBOX","TELEGRAM","ALISA","VK"]} || name = "bon-jovi-its-my-life.mp3"
        buttons:
            "Еще музыки?" -> /Выбрать музыку
            "Закончим веселье?" -> /Bye

    state: NewState_8
        audio: https://248305.selcdn.ru/zfl_prod/251178517/251178516/audio/EavfpfJE3xq44T8Q.mp3?channels={"incompatible":["OUTGOING_CALLS","ALEXA","GOOGLE_ASSISTANCE"],"compatible":["FACEBOOK","WHATSAPP","AIMYBOX","TELEGRAM","ALISA","VK"]} || name = "Малиновая лада.mp3"
        buttons:
            "Еще музыки?" -> /Выбрать музыку
            "Закончим веселье?" -> /Bye

    state: Jaz
        audio: https://ringtonazer.b-cdn.net/fetch/8b/8beeaf93be017984a27e70d9c2de6154.mp3 || name = "8beeaf93be017984a27e70d9c2de6154.mp3"
        buttons:
            "Еще музыки" -> /Выбрать музыку
            "Закончим веселье?" -> /Bye