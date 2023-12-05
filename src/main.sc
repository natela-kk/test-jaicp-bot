require: slotfilling/slotFilling.sc
  module = sys.zb-common
require: patterns.sc
theme: /

    state: NoMatch || sessionResult = "Тут обрабатываем непонятные запросы", sessionResultColor = "#3E8080" 
        event!: noMatch
        a: Простите, я вас не поняла. Не могли бы вы уточнить вопрос? Вы сказали "{{$request.query}}". || html = "Простите, я вас не поняла. Не могли бы вы уточнить вопрос? Вы сказали "{{$request.query}}"."
        go!: /Меню

    state: Часы работы || sessionResult = "Отвечаем про часы работы", sessionResultColor = "#15952F"
        a: Мы работаем с 10 утра до 8 вечера по будням и с 11 до 17 в субботу. Воскресенье — выходной. || htmlEnabled = true, html = "Мы работаем <b>с 10 утра до 8 вечера</b> по будням и с 11 до 17 в субботу. Воскресенье — выходной."
        go!: /Меню

    state: Отзыв о работе || sessionResult = "Отзыв о работе", sessionResultColor = "#7E47D1"
        a: Пожалуйста, оцените нашу работу. || htmlEnabled = false, html = "Мы будем благодарны услышать ваш отзыв о работе."
        buttons:
            "Все отлично, спасибо большое" -> /Оценка Отлично
            "Вам есть что улучшить" -> /Уточнение причины
            "Все очень плохо" -> /Уточнение причины
        intent: /Отличная оценка || onlyThisState = false, toState = "/Оценка Отлично"
        intent: /Плохая оценка || onlyThisState = false, toState = "/Уточнение причины"
        intent: /Средняя оценка || onlyThisState = false, toState = "/Уточнение причины"

    state: Оценка Отлично || sessionResult = "Отзыв о работе", sessionResultColor = "#7524AA"
        a: Спасибо за обратную связь! Мы рады работать для вас
        go!: /Меню

    state: Уточнение причины || sessionResult = "Отзыв о работе", sessionResultColor = "#7524AA"
        a: Мне очень жаль, что мы вас расстроили :( В следующий раз будем стараться лучше.
        go!: /Меню

    state: Меню || noContext = true
        a: Я могу чем-нибудь еще вам помочь? || htmlEnabled = false, html = "Я могу чем-нибудь еще вам помочь?"
        buttons:
            "Оставить отзыв" -> /Отзыв о работе
        intent: /Оставить отзыв || onlyThisState = false, toState = "/Отзыв о работе"
    state: Адреса || modal = true
        a: Выберете отдел, режим работы которого вы хотите уточнить
        buttons:
            "Владимирская" -> /Часы работы
            "Петроградская" -> /Часы работы
        state: Выбрать адрес
            q: * ~Владимирская *
            q: * ~Петроградская *
            
            state: NoMatch
                event: noMatch
                a: По адресу "{{$request.query}}" ничего не найдено

    state: Start || sessionResult = "Сценарий начинается отсюда", sessionResultColor = "#143AD1"
        q!: $regex</start>
        q!: * $hello *
        q: * (отмен*/стоп) * || fromState = ./Адреса
        image: https://248305.selcdn.ru/zfl_prod/64069/64072/Y6nDSc64tgJWac7N.png
        a: Добрый день! Я виртуальный секретарь компании Искра. Я могу рассказать о времени работы офиса и уточнить статус заказа. Также вы можете оставить мне обратную связь о работе нашей компании
        buttons:
            "Часы работы" -> /Адреса
            "Оставить отзыв" -> /Отзыв о работе
        intent: /Оставить отзыв || onlyThisState = false, toState = "/Отзыв о работе"
        intent: /Часы работы || onlyThisState = false, toState = "/Адреса"