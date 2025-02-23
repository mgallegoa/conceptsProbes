FROM node:22-alpine3.20

WORKDIR /app/react-new-features

COPY package.json .
RUN npm install -g pnpm@latest-10 && pnpm install

COPY . .

# RUN pnpm build

CMD ["pnpm", "start"]


