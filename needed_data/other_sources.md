# 🔗 ShortURL – URL Shortening Service with Weekly Email Report

ShortURL bu kichik, ammo kuchli Spring Boot ilova bo‘lib, foydalanuvchilarga URL manzillarini qisqartirish imkonini beradi. Har hafta, foydalanuvchilarga qisqartirilgan URL’lar haqida to‘liq e-mail report yuboriladi.

## 🚀 Texnologiyalar
- **Spring Boot 3**
- **Spring Security + JWT**
- **JPA + Hibernate**
- **MySQL (PostgreSQL ham ishlaydi)**
- **Freemarker Email Templates**
- **Swagger UI (API testing uchun)**

## 🔐 Authentication
- JWT asosida tokenlash
- Ro'yxatdan o'tish, aktivatsiya, tizimga kirish va chiqish
- `Bearer` token orqali API himoyalangan

## 📬 Weekly Report
- Har bir foydalanuvchiga qisqartirilgan URL’lar haqida haftalik e-mail yuboriladi
- HTML formatidagi email (`Freemarker` yordamida)
- Hisobotda URL’lar, kun, va yaroqlilik holati ko‘rsatiladi

## 📦 API Endpoints
| Endpoint | Method | Auth | Description |
|----------|--------|------|-------------|
| `/api/auth/register` | POST | ❌ | Foydalanuvchini ro‘yxatdan o‘tkazish |
| `/api/auth/activate/{code}` | GET | ❌ | Email orqali aktivatsiya |
| `/api/auth/login` | POST | ❌ | Login va JWT olish |
| `/api/url` | POST | ✅ | URL qisqartirish |
| `/{code}` | GET | ❌ | Kichik URL orqali redirect qilish |
| `/api/url/report` | GET | ✅ | Haftalik email report yuborish |

## 🧪 Testing
- Swagger orqali barcha endpointlar test qilinadi
- Test ma’lumotlar mavjud

## 📄 License
MIT