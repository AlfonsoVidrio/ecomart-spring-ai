# Ecomart AI Services

Este proyecto proporciona servicios impulsados por inteligencia artificial para generación de imágenes, categorización de productos y generación de listas de productos utilizando **Spring AI**.

## Características

- **Generación de Imágenes**: Genera imágenes basadas en prompts utilizando el modelo de IA.
- **Categorización de Productos**: Categoriza productos en base a su descripción usando el modelo `gpt-4o-mini`.
- **Generación de Productos**: Genera listas de productos con atributos como nombre, precio y descripción.

## Configuración

### Prerrequisitos

- Java 17+
- Spring Boot
- API Key de OpenAI

### Variables de Entorno

Antes de ejecutar la aplicación, necesitas configurar la API key de OpenAI en tu entorno. Esto se hace estableciendo la variable de entorno `OPENAI_API_KEY` con tu clave de API de OpenAI.

