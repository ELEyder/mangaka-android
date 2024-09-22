# Desarrollo de una Aplicación Móvil de Lectura Digital de Mangas y Libros

**Autor:** Eyder Huayta Tantavilca  
**Institución:** Servicio Nacional de Adiestramiento en el Trabajo Industrial

---

## Nota del Autor
No se ha recibido ninguna beca o ayuda financiera para el desarrollo de este proyecto.

## Resumen
La aplicación móvil en desarrollo está diseñada para facilitar la lectura de mangas, libros y cómics, abordando la falta de alternativas simples y accesibles en el mercado. Creada por necesidad propia, la aplicación permite a los usuarios seleccionar entre diferentes tipos de contenido, con planes futuros para incorporar un sistema de inicio de sesión con Google y opciones adicionales. Actualmente, la aplicación está destinada únicamente a la plataforma Android y está orientada a jóvenes interesados en la lectura. Su principal ventaja es su interfaz intuitiva y gratuita, promoviendo el hábito de lectura entre los usuarios.

## 1. Requisitos del Sistema

### 1.1 Requisitos Funcionales
- Selección y lectura de mangas, libros y cómics.
- Funcionalidades básicas de navegación y personalización.
- Sistema de cuentas con inicio de sesión con Google (futuro).

### 1.2 Requisitos No Funcionales
- **Rendimiento:** La aplicación debe cargar el contenido rápidamente y ser fluida durante la lectura.
- **Usabilidad:** Debe ser fácil de usar y accesible para jóvenes y nuevos lectores.
- **Seguridad:** Protección de datos del usuario.

### 1.3 Requisitos Técnicos
- **Lenguajes de Programación:** Java para desarrollo en Android.
- **Herramientas de Desarrollo:** Android Studio para programación, Figma para diseño.

## 2. Diseño de la Aplicación
- **Pantallas:** Inicio, elección de libros, cómics, mangas y páginas de lectura.

## 3. Plan de Desarrollo

### 3.1 Fases del Proyecto
1. **Planificación:** Definición de objetivos y requisitos.
2. **Diseño:** Creación de wireframes y mockups.
3. **Desarrollo:** Programación e implementación de funcionalidades.
4. **Pruebas:** Verificación de la aplicación y corrección de errores.
5. **Despliegue:** Lanzamiento en Google Play Store.

### 3.2 Herramientas de Gestión
- GitHub para control de versiones y colaboración. [Repositorio](https://github.com/ELEyder/mangaka-app.git)

## 4. Implementación

### 4.1 Tecnologías Utilizadas
- **Android Studio:** Entorno de desarrollo para programación en Android.
- **Figma:** Herramienta de diseño para crear la interfaz de usuario.

### 4.2 Configuración del Entorno de Desarrollo
1. Instalar Android Studio.
2. Clonar el repositorio GitHub.
3. Elegir el dispositivo Android (Emulador o Dispositivo conectado).
4. Descargar e instalar la API MangaHock.
5. Instalar dependencias de la API.
6. Inicializar el servidor local en el puerto 3000.
7. Crear un puente de enlace al puerto de la API.
8. Cambiar el enlace de la API en `ApiManga.class`.
9. Ejecutar el proyecto.

## 5. Pruebas

### 5.1 Estrategia de Pruebas
- **Pruebas Unitarias:** Verificación de cada módulo.
- **Pruebas de Integración:** Asegurar el funcionamiento conjunto de componentes.
- **Pruebas de Aceptación:** Validar cumplimiento de requisitos.

### 5.2 Casos de Prueba
- Verificar la carga de un manga.
- Validar el funcionamiento de la API Manga Hock.
- Validar el sistema de inicio de sesión (futuro).

## 6. Manual de Usuario

### 6.1 Guía de Uso
1. **Descargar e Instalar la Aplicación:**
   - Ingresa al enlace de la aplicación.
   - Descarga e instala el APK.
   - Abre la aplicación desde tu pantalla de inicio.

2. **Primer Inicio:**
   - Pantalla de bienvenida para familiarizarte con las funciones.

3. **Seleccionar Tipo de Contenido:**
   - Elige entre Mangas, Libros y Cómics.

4. **Leer un Manga o Libro:**
   - Accede a la página de capítulos y selecciona uno.

5. **Navegar por el Contenido:**
   - Desliza hacia arriba o abajo para pasar de página.

### 6.2 Preguntas Frecuentes (FAQ)
- **¿Cómo inicio sesión?** No es necesario tener una cuenta.
- **¿Cómo reporto un problema?** A través del repositorio de GitHub.
- **¿Por qué no cargan los mangas?** Puede ser por inactividad de la API o conexión lenta a Internet.

## 7. Desarrollo de Funcionalidades

### 7.1 Clases Principales
- **ApiManga:** Encargada de peticiones a la API.
- **MainActivity:** Pantalla de inicio que solicita un nombre de usuario.
- **MenuActivity:** Menú principal con opciones de contenido.
- **MangasActivity:** Lista de mangas obtenidos de la API.
- **CapitulosActivity:** Muestra los capítulos de un manga.
- **LecturaActivity:** Muestra las imágenes del capítulo seleccionado.

### 7.2 Flujo de la Aplicación
1. Inicio de sesión en `MainActivity`.
2. Acceso a `MenuActivity`.
3. Visualización de mangas en `MangasActivity`.
4. Lectura en `LecturaActivity`.

## 8. Conclusiones

### 8.1 Resultados Esperados
Se espera que la aplicación facilite el acceso a mangas, libros y cómics, promoviendo la lectura entre jóvenes.

### 8.2 Futuras Mejoras
- Expansión de contenidos y funcionalidades adicionales.

## 9. Referencias

### 9.1 Documentación de Librerías
- [VOLLEY: Documentación de Volley](https://developer.android.com/training/volley)
- [PICASSO: Documentación de Picasso](https://square.github.io/picasso/)
- [GSON: Documentación de Gson](https://github.com/google/gson)

### 9.2 API Externa
- [API de Mangas: Documentación de Manga Hock](https://mangahook-api.vercel.app/installation)

## Documentación Adicional
- **Código Fuente:** [Repositorio GitHub](https://github.com/ELEyder/mangaka.git)
- **Figma:** [Diseño de la Aplicación](https://www.figma.com/design/B9Ly4VOwqk7jeX8xZibe1u/MANGAKA-APP?node-id=26-22&t=a3HoZdilDePcN4eF-1)

