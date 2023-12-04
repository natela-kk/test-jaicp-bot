require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Добрый день! Я бот-помощник. Чем я могу помочь?
        buttons:
            "Заказать бота" -> /Information request
            {text: "Наш сайт", url: "https://example.com"}

    state: NoMatch
        event!: noMatch
        a: Извините, я Вас не понял. Перефразируйте, пожалуйста.

    state: Match
        event!: match
        a: {{$context.intent.answer}}

    state: Information request
        InputText: 
            prompt = Расскажите нам, какого бота Вы хотели бы заказать. Обязательно оставьте свои контактные данные.
            varName = order
            html = 
            htmlEnabled = false
            actions = {}
            then = /Order confirmation

    state: Order confirmation || sessionResult = "Заявка создана", sessionResultColor = "#15952F"
        a: Ваша заявка: «{{$session.order}}». Спасибо за информацию! Менеджер свяжется с Вами в ближайшее время.