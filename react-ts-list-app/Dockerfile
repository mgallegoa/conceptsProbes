FROM node:22.13-alpine3.21 as BUILD_IMAGE

WORKDIR /app/react-ts-list-app

COPY package.json .

RUN npm install -g pnpm@latest-10 && pnpm install

# Copy the application
COPY . .

# EXPOSE 5555
RUN pnpm run build


# Multi-stage build
FROM node:22.13-alpine3.21 as PRODUCTION_IMAGE
WORKDIR /app/react-ts-list-app
COPY --from=BUILD_IMAGE /app/react-ts-list-app/dist/ /app/react-ts-list-app/
COPY package.json .
COPY vite.config.ts .

RUN pnpm install typescript

# This app run in the internal port 5174, go to README.md
CMD ["pnpm", "run", "preview"]


