require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Добрый день! Я бот-помощник. Чем я могу помочь?
        buttons:
            {text: "Заказать бота", url: "https://example.com"}
            {text: "Наш сайт", url: "https://example.com"}

    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}

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