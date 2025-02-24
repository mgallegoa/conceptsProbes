# React + TypeScript + Vite - List App

               ███╗░░░███╗░█████╗░███╗░░██╗██╗░░░██╗███████╗██╗░░░░░
               ████╗░████║██╔══██╗████╗░██║██║░░░██║██╔════╝██║░░░░░
               ██╔████╔██║███████║██╔██╗██║██║░░░██║█████╗░░██║░░░░░
               ██║╚██╔╝██║██╔══██║██║╚████║██║░░░██║██╔══╝░░██║░░░░░
               ██║░╚═╝░██║██║░░██║██║░╚███║╚██████╔╝███████╗███████╗
               ╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝░╚═════╝░╚══════╝╚══════╝

## Description

Generate with a vite template for a minimal setup to get React.

This app add items to a list.

Create with React and Typescript and testing with vitest

To run the project (or see the [Docker section](#DOCKER) in this file):

```
    pnpm install
    pnpm run dev
```
Go to the url: http://localhost:5174/

To run the project in docker [Read this section](#DOCKER)

## TESTING

For testing using vistest (alternative to jest) , happy-dom (to emulate the browser)

To render react components, use Testing Library React,

For End to End testing add the library @testing-library/user-event

## CUSTOM HOOK

Custom Hook for manage the Item state

## SEO

Create a hook to add SEO to the page

## DOCKER

Use the Dockerfile, this app run in the internal port 5174, use:

1. docker build -t manuelarias/react-ts-list:v1 .
2. docker run -dp 5555:5174 --name react-ts-list-app -v /media/manuel/Datos/mgallegoa/conceptsProbes/react-ts-list-app/src:/app/react-ts-list-app/src manuelarias/react-ts-list:v1
3. docker exec -it react-ts-list-app bash

## DOCKER - Compose

- Development service: docker-compose up -d dev-react-ts-list-app
- Production build service: docker-compose up -d prod-build-react-ts-list-app --build
- Production run service: docker-compose up -d prod-run-react-ts-list-app

Note: use --build if previously exist the image, otherwise (the image don't exist) the parameter is not required

## HTTP and HTTPS

To view working correctly, it is necessary to run over HTTPS secure protocol.

This is necessary for the crypto.randomUUID() method, the method is accessible for the browser only in https session.

For testing in a Play With Docker page or Oracle Cloud, you can use cloudflare (WARP).

# Run in Play With Docker PWD:

docker run -dp 8080:80 --name react-ts-list-app manuelarias/react-ts-list:v2

# Instructions to install WARP (cloudfare CLI) in an Alpine Linux server (Play With Docker use it):

1. Download the Binary:
   wget https://github.com/cloudflare/cloudflared/releases/latest/download/cloudflared-linux-amd64 -O /usr/local/bin/cloudflared
2. Set Execute Permissions:
   chmod +x /usr/local/bin/cloudflared
3. Verify Installation:
   cloudflared -v
4. Create the Cloudflared tunel:
   cloudflared tunnel --url http://localhost:8080
   (This give a page similar to https://lauren-tones-incorrect-sonic.trycloudflare.com/)

# Instructions to install Oracle Command Infrastructure CLI in a machine running Alpine Linux:

1. Ensure your /etc/apk/repositories file includes the community repository. Add the following line if it's missing:
   http://dl-cdn.alpinelinux.org/alpine/v3.20/community
2. apk update
3. apk add oci-cli
4. oci -v

## Vite documentation

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react/README.md) uses [Babel](https://babeljs.io/) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

## Expanding the ESLint configuration

If you are developing a production application, we recommend updating the configuration to enable type aware lint rules:

- Configure the top-level `parserOptions` property like this:

```js
export default tseslint.config({
  languageOptions: {
    // other options...
    parserOptions: {
      project: ["./tsconfig.node.json", "./tsconfig.app.json"],
      tsconfigRootDir: import.meta.dirname,
    },
  },
});
```

- Replace `tseslint.configs.recommended` to `tseslint.configs.recommendedTypeChecked` or `tseslint.configs.strictTypeChecked`
- Optionally add `...tseslint.configs.stylisticTypeChecked`
- Install [eslint-plugin-react](https://github.com/jsx-eslint/eslint-plugin-react) and update the config:

```js
// eslint.config.js
import react from "eslint-plugin-react";

export default tseslint.config({
  // Set the react version
  settings: { react: { version: "18.3" } },
  plugins: {
    // Add the react plugin
    react,
  },
  rules: {
    // other rules...
    // Enable its recommended rules
    ...react.configs.recommended.rules,
    ...react.configs["jsx-runtime"].rules,
  },
});
```
