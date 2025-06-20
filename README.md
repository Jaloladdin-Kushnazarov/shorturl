# 🔗 ShortURL – URL Shortening Service with Weekly Email & Telegram Logging

ShortURL bu kuchli va zamonaviy URL qisqartirish backend servisi bo‘lib, foydalanuvchiga quyidagi imkoniyatlarni taqdim etadi:
- URL manzillarni qisqartirish
- Kichik link orqali foydalanuvchini to‘g‘ri yo‘naltirish (redirect)
- Har haftalik e-mail orqali URL hisobotini yuborish (Freemarker orqali yaratilgan HTML dizayn)
- Telegram bot orqali xatolik loglarini yuborish – monitoring osonlashadi

## 🚀 Texnologiyalar
- **Java 21**, **Spring Boot 3.x**
- **Spring Security + JWT Authentication**
- **Spring Data JPA + Hibernate**
- **MySQL / PostgreSQL**
- **Freemarker (Email templates)**
- **Swagger UI (OpenAPI)**
- **Telegram Bot log integratsiyasi (Logback Appender orqali)**

## 🔐 Authentication
- JWT asosida stateless authentication
- Registratsiya, faollashtirish, tizimga kirish
- Swagger orqali token bilan API testlash

## 📬 Weekly Report
- Har bir foydalanuvchiga haftalik e-mail yuboriladi
- HTML formatdagi chiroyli report (kunlik statistikalar bilan)
- Email Template: `report.ftlh`

## 🤖 Telegram Logging
- Logback orqali barcha `ERROR` loglar Telegram bot orqali yuboriladi
- Adminlar uchun monitoring qulay va real vaqtli

## 📦 API Endpoints
| Endpoint | Method | Auth | Description |
|----------|--------|------|-------------|
| `/api/auth/register` | POST | ❌ | Ro‘yxatdan o‘tish |
| `/api/auth/activate/{code}` | GET | ❌ | Emailni faollashtirish |
| `/api/auth/login` | POST | ❌ | Tizimga kirish |
| `/api/url` | POST | ✅ | URL qisqartirish |
| `/{code}` | GET | ❌ | Redirect |
| `/api/url/report` | GET | ✅ | Haftalik hisobot yuborish |

## ⚙️ Arxitektura Yondashuvi
- `CustomUserDetails` va `SessionUser` orqali token user ma’lumotlarini olish
- `MailSenderService` orqali async email yuborish
- `UrlService` orqali URL’lar bilan ishlash
- `JwtFilter` orqali barcha request’larni intercept qilish

## ✅ TODO / NEXT STEPS
- Redis caching qo‘shish (performance uchun)
- Rate-limiting (DoS oldini olish uchun)
- URL analytics (dashboard uchun)

## 🧪 Swagger UI
Swagger orqali barcha endpointlar test qilinadi:  
`http://localhost:8080/swagger-ui.html`

## 📄 License
MIT
