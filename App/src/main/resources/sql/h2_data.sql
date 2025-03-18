INSERT INTO herb_order (consumer_id, consumer_name, consumer_email, city, herb, quantity, delivery_date) VALUES
    (1, 'Иван Иванов', 'ivan@example.com', 'Москва', 'Мелисса', 10, '2024-10-15'),
    (2, 'Мария Петрова', 'maria@example.com', 'Санкт-Петербург', 'Мелисса', 5, '2024-10-20'),
    (3, 'Алексей Сидоров', 'alexey@example.com', 'Екатеринбург', 'Шалфей', 8, '2024-10-25'),
    (4, 'Ольга Смирнова', 'olga@example.com', 'Новосибирск', 'Череда', 15, '2024-10-30');


INSERT INTO herb (name, features, usage, image_path, remains) VALUES
    ('Ромашка',
    'Ромашка обладает противовоспалительным и успокаивающим действием. Чай из ромашки помогает при бессоннице и стрессе.',
    'Настой ромашки используется для полоскания горла, промывания глаз, лечения кожных заболеваний.',
    '/ромашка.jpg',
    100),
    ('Зверобой',
    'Зверобой известен своими антидепрессивными и заживляющими свойствами. Он также помогает при заболеваниях желудочно-кишечного тракта.',
    'Зверобой используется в виде настоя или масла для лечения ран, ожогов и различных воспалений.',
    '/зверобой.jfif',
    150),
    ('Мята',
    'Мята известна своими освежающими, успокаивающими и обезболивающими свойствами. Она помогает улучшить пищеварение и снимает головные боли.',
    'Мяту используют в виде чая для успокоения, при тошноте и желудочных спазмах. Эфирное масло мяты помогает при головной боли и используется в ароматерапии.',
    '/мята.jfif',
    200);
