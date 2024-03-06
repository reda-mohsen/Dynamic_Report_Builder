CREATE TABLE IF NOT EXISTS fbnk_customers (
    CustomerID INT PRIMARY KEY,
    Name_EN VARCHAR(255),
    Name_AR VARCHAR(255),
    Phone_Number VARCHAR(20),
    Address_EN VARCHAR(255),
    Address_AR VARCHAR(255)
);

INSERT INTO fbnk_customers (CustomerID, Name_EN, Name_AR, Phone_Number, Address_EN, Address_AR)
VALUES
(1, 'Reda Mohsen', 'رضا محسن', '1117532355', '7 Mohamed El Sayed St', '7 شارع محمد السيد'),
(2, 'Ahmed Ali', 'أحمد علي', '2229876543', '15 Main St', '15 شارع الرئيسي'),
(3, 'Fatima Hassan', 'فاطمة حسن', '3338765432', '22 Park Avenue', '22 شارع بارك أفينيو'),
(4, 'Sarah Smith', 'سارة سميث', '4441122334', '5 Elm Street', '5 شارع الدردارة'),
(5, 'Mehmet Yilmaz', 'محمد يلماز', '5554455667', '18 Oak Avenue', '18 شارع البلوط'),
(6, 'Elena Petrova', 'إيلينا بيتروفا', '6667788990', '10 Birch Lane', '10 شارع البتول'),
(7, 'Juan Rodriguez', 'خوان رودريغيز', '7776655443', '3 Pine Road', '3 شارع الصنوبر'),
(8, 'Aisha Rahman', 'عائشة رحمان', '8889876543', '25 Maple Street', '25 شارع القيقب'),
(9, 'Dmitri Ivanov', 'دميتري إيفانوف', '9998765432', '12 Cedar Drive', '12 شارع الأرز'),
(10, 'Lila Chen', 'ليلى تشن', '1012345678', '8 Walnut Lane', '8 شارع الجوز');


