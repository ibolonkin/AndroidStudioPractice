package com.example.practice3.news.presentation

import com.example.practice3.news.presentation.model.FilmsUiModel

object MockData {
    fun getFilms(): List<FilmsUiModel> = listOf(
        FilmsUiModel(
            id = 1,
            title = "1+1",
            descr = "Аристократ на коляске нанимает в сиделки бывшего заключенного. Искрометная французская комедия с Омаром Си",
            year = "2011",
            imageUrl = "https://avatars.mds.yandex.net/get-ott/236744/2a00000198530fb3e592ad08b06f9b81d22b/300x450"
        ),
        FilmsUiModel(
            id = 2,
            title = "Интерстеллар",
            descr = "Когда засуха, пыльные бури и вымирание растений приводят человечество к продовольственному кризису, коллектив исследователей и учёных отправляется сквозь червоточину (которая предположительно соединяет области пространства-времени через большое расстояние) в путешествие, чтобы превзойти прежние ограничения для космических путешествий человека и найти планету с подходящими для человечества условиями.",
            year = "2014",
            imageUrl = "https://avatars.mds.yandex.net/get-kinopoisk-image/1600647/430042eb-ee69-4818-aed0-a312400a26bf/300x450"
        ),
        FilmsUiModel(
            id = 3,
            title = "Побег из Шоушенка",
            descr = "Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения.",
            year = "1994",
            imageUrl = "https://avatars.mds.yandex.net/get-kinopoisk-image/1599028/0b76b2a2-d1c7-4f04-a284-80ff7bb709a4/300x450"
        ),
        FilmsUiModel(
            id = 4,
            title = "Джентльмены",
            descr = "Один ушлый американец ещё со студенческих лет приторговывал наркотиками, а теперь придумал схему нелегального обогащения с использованием поместий обедневшей английской аристократии и очень неплохо на этом разбогател. Другой пронырливый журналист приходит к Рэю, правой руке американца, и предлагает тому купить киносценарий, в котором подробно описаны преступления его босса при участии других представителей лондонского криминального мира — партнёра-еврея, китайской диаспоры, чернокожих спортсменов и даже русского олигарха.",
            year = "2019",
            imageUrl = "https://avatars.mds.yandex.net/get-ott/2385704/2a0000019854f2d5c8ce13461055033ab990/300x450"
        ),
        FilmsUiModel(
            id = 5,
            title = "Зеленая миля",
            descr = "Пол Эджкомб — начальник блока смертников в тюрьме «Холодная гора», каждый из узников которого однажды проходит «зеленую милю» по пути к месту казни. Пол повидал много заключённых и надзирателей за время работы. Однако гигант Джон Коффи, обвинённый в страшном преступлении, стал одним из самых необычных обитателей блока.",
            year = "1999",
            imageUrl = "https://avatars.mds.yandex.net/get-kinopoisk-image/1599028/4057c4b8-8208-4a04-b169-26b0661453e3/300x450"
        ),
        FilmsUiModel(
            id = 6,
            title = "Остров проклятых",
            descr = "Два американских судебных пристава отправляются на один из островов в штате Массачусетс, чтобы расследовать исчезновение пациентки клиники для умалишенных преступников. При проведении расследования им придется столкнуться с паутиной лжи, обрушившимся ураганом и смертельным бунтом обитателей клиники.",
            year = "2009",
            imageUrl = "https://avatars.mds.yandex.net/get-ott/224348/2a00000198528f853134273ea785844e1c8a/300x450"
        ),
        FilmsUiModel(
            id = 7,
            title = "Форрест Гамп",
            descr = "Сидя на автобусной остановке, Форрест Гамп — не очень умный, но добрый и открытый парень — рассказывает случайным встречным историю своей необыкновенной жизни.\n" +
                    "\n" +
                    "С самого малолетства парень страдал от заболевания ног, соседские мальчишки дразнили его, но в один прекрасный день Форрест открыл в себе невероятные способности к бегу. Подруга детства Дженни всегда его поддерживала и защищала, но вскоре дороги их разошлись.",
            year = "1994",
            imageUrl = "https://avatars.mds.yandex.net/get-kinopoisk-image/1599028/3560b757-9b95-45ec-af8c-623972370f9d/300x450"
        ),
        FilmsUiModel(
            id = 8,
            title = "Декстер",
            descr = "Я — Декстер. Декстер Морган. Я работаю судмедэкспертом в полиции Майами. Я не понимаю любви, мне безразличен секс, и у меня нет чувств. А еще я серийный убийца.\n" +
                    "\n" +
                    "Мой отец был полицейским и прекрасно обучил меня прятать улики. Обычным гражданам не стоит меня бояться, я убиваю только преступников, подонков, до которых почему-либо не смогла или не захотела добраться полиция. Я убиваю их, аккуратно распиливаю на части и избавляюсь от трупов. Убийство для меня — творческий акт, искусство.\n" +
                    "\n" +
                    "Но однажды… В Майами появляется некто, равный мне. Даже превзошедший меня. Сумевший меня заинтересовать. Предложивший мне своего рода товарищеское состязание. Кто этот загадочный и неуловимый «икс»?..",
            year = "2006 – 2013",
            imageUrl = "https://kinopoisk-ru.clstorage.net/E1X7c0131/9f2a5d5Xm/pwMBaE92elLg8D5C2KuWnBgha5dJlnfxqoMU32ZQKy57F-JADL7SQsAMKqtmIhlmgTmSHUQzMGzio3BIhs_4z-dXCHUaf29ntYVON_3vz91ZAK9T2UPDEQIahH-SuUo7a3aTNWW_2nulAUbSbzOA-tScQ0gVRun4qgKw1Tv2wJbrby0o4yqHrvT476qYO85kVAizP01L8-Fu9sBDCj3ag6ZSi7lB41772WHngscnHCfY6JuAtT6xgMYmDMnHzjBGk2-N-I-ag0KcXHtKtD-OVDRQh5cV40-5-l6oXwrVDg_-clupSdu6gzUlg8r3szQ7hJybbKEK0ZTqvvwdu3oEMpOPKWSDblfyiOhj8mRnerDIeBPPbdMf_W5ykEuCLdp_Znp7OY1XuhdRFd9660vYDwzYx1y9qlmoJg6ctWOaHFIr8-EsZzYbLtA0I94cPwLkpCTn82H7Y91uCmDPfiGeq4qekwFtU6Z_tRW3fuNrWOdITHcQrcaVKP6aqDnbdhyOgw9FPIcS8ybkQO_uXKPisEwQsyfZp5_hgmocL75Vdq-eAn81UQf6ox2tL1bDWyCjEEj_ZJE63dBesiDxT_YkTnObxWR3YpOqUPSjsnynskzQdI8PYafXUZ7aLNPyDU4rMo6jBYUXBlflZTsmI09YA0RQZ_BVxkkYUi6YNTeWbHa_ewmsV5orKpx4I34s_yYAOJzzA0HT7wl28rh7PulWSwomM8WRP8ojWZlDgu_HyNO8LMNYIWJJaKKu_P23lrTi8xsVYOteM-aEtNdSiCOeICBk--sR1yMFlla891ZdrgPeKv-ZfcOy58FZ_8oje8hLzJxv-K3qMdhicqiBg6pkuhv7PTyXEhduvHTjrqBrEuRIAEe76RdbhaZGwHPiOaZznpo3zXWLis8dAUfaM8fM2wTU92idasUQRo6YaS8uEII_m7V4b26fNkAA1x5sN2bYoAxby_Fng60eRjwz9lmCs-oK67ntM66jnfF7jiPbSKfwTOPkFUolKCIecOHruriykw_FKJ8uswI8dH_eVOc2yJxolzPtf_elGm6411612gfypptRhau-yzklj3ZrP-ivyPgfCL06QfzGqsDJX7asEguLjSSjfieiWLwbPih_tiyIoBf_ZVuPsTayuHeyQQK7qkYPNWlLCkuFBb-C42-0T8zsv2wVItH8InJYie8myJKXn91884qHclzQJ35YT7JMKIDDhwVXn7F-cvh7skGeR1bKq4kJS7Z7XfXDjoP_MLNccBeEQS4ZbMqCXGW3opDep8spgOsa84KU6CNOICMinAhwyycV05O9nsaA83Zlgl-a2v95NcfKmyUFL1IXo3Tb-GAb1JkKdQTmisgxJ3ZM8nvrAXyTDhu66PB_bqQn5rCYxJfrPWNXieb6PItWoaqjfkYfXWnjonOFSXfK79s454AkD9y53h2U1t5c-e9eJA4b66kUCybf-gBob874uzbcbCBbixV3T-kyMnznPmXeN_ZGh1UZEy4HCdVnih_bNNfIPDcIPWrBmDIycInrihhysz9BPA8Oqwr8NBdCmHc6qKj0829hi4-JDsZsf6bZNkue8o-BYUdym00dq643j3gfhFyD2Gku3ejCorRxV6I0dmMPmdx3Do8ugGyn1iT3lphY8JO_tdfv0S5-GH-6DdbjGmJXXVHbjhuNefdmL6s8WzTkU-hBUuWwsiakDQOqYArzw8E0w3Y70hgw91JYI-roiCDHG3FPAzUq2qw77umaM27al509oyaviQXTyhMDcN_wKMNQ6TJxLCrqyBE_PnRSY5dlNH-uP0KMZNPW-A92GABgxxdR8yv5dmoot3Y5utMOfq_FhZPe37X5e97jT5zffFz7-C1S0bQSYijFS1LMCr9_sRRDhmcO-Ei_tgyjbsikgNczxQdvNRaGaKPmPa4DfkLrXdHryp9FkeeuLwOgGxise3wdQmk89tKM8fv-FG4Hn9m8l6IPXmSsK9Z8_340RPRn-0nPa3V-ciz_BqnOO9LCI6Vhu5aXPd1HVov7wJPsyFsgzU616OYWpEVjmgzW53s5YKs2F05IoK8q4BdyxFggu0uN31M5YgIAtwZBXleiWqM1WQu-m11xv17za0xb0GRfULk-oeRGmpiZt1awkm9rpTT3tuu-FPz_cvyrgrQ00BtjjcPfjTa69K-mScJrauYn2UVTTg-NaaPS9wfE2zwcS9QReuXg2gp8_de2nMJj-40AH4qv1kBE1xKAJzIAyFT7_1EHz6mKAjzrdk0Gawby86GRV97v3f3DyiNzGOeYQPvgQVpZYLISHNl3prB-SxeNpH9-u_60SH_GhMceAKzcdyP9n2ulEsLI855Rslu6DqsF6ZOepxF5f37HS9xTYMwL9CEu2SA6DihJL064cr9vyQxbijMyXGjLTpAP-kgY6IeD5csLPV6y6K-GzTY3cpKbKfGjWhNpUdM252-Um5g8y6DRwp0IuuaQzffWDH6Xb5WQe3orrpyEG0psZxKIzPyPx7mnW1XyagCrjlFOp2Ye-w1pnzLbJS0D2uvP8Md4zD-MJT41lP7-dLGHgrzKLxM1iGcuP-ZwyF9GmBNiAMgEQ0dhT58x1uqEuwKtBtui2oupTccyUz3Jg15_26i75LxDELmisezS6oyZ00rwXu9HvQjjJrsilPCbJqz7RthskFefWY8LdQoCbPceOfbLrm6PnQ3Xyk9dZb827z_Ao0iwYyyl6hUMKg50kUuOoJpz5yEYqxYPgpR0p078f_YAhOCfb0lP49m6EjA79sHG86ra8wH5awIThWkz1v_HaFewRMt46TLZfFoudN2Ppize6w-BcBP-j6I0-FvWjP82FDx8F0_NT3vhbn4kW2pNpnfSGg958b-SJ1kJwwI3_6gPTCxH6GFa8ZjyBjDZIwrovmdrHZB3fhPinFhHSuxrjkCw7GMbbWv3UY6yiK-6Ud5foiYfDcErRsORyW92u_-oOwjoPxQhhiGYtiaQ",
        ),
        FilmsUiModel(
            id = 9,
            title = "Бойцовский клуб",
            descr = "Сотрудник страховой компании страдает хронической бессонницей и отчаянно пытается вырваться из мучительно скучной жизни. Однажды в очередной командировке он встречает некоего Тайлера Дёрдена — харизматического торговца мылом с извращенной философией. Тайлер уверен, что самосовершенствование — удел слабых, а единственное, ради чего стоит жить, — саморазрушение.\n" +
                    "\n" +
                    "Проходит немного времени, и вот уже новые друзья лупят друг друга почем зря на стоянке перед баром, и очищающий мордобой доставляет им высшее блаженство. Приобщая других мужчин к простым радостям физической жестокости, они основывают тайный Бойцовский клуб, который начинает пользоваться невероятной популярностью.",
            year = "1999",
            imageUrl = "https://avatars.mds.yandex.net/get-kinopoisk-image/4716873/85b585ea-410f-4d1c-aaa5-8d242756c2a4/300x450"
        ),
        FilmsUiModel(
            id = 10,
            title = "Брат",
            descr = "Демобилизовавшись, Данила Багров возвращается в родной городок. Но скучная жизнь провинции его не устраивает, и он отправляется в Петербург, где, по слухам, уже несколько лет процветает его старший брат. Данила находит брата, но всё оказывается не так просто — брат работает наемным убийцей.",
            year = "1997",
            imageUrl = "https://avatars.mds.yandex.net/get-ott/12808873/2a0000019854a9569428328fa99b3f0e16d3/300x450"
        )
    )
}