# Étape 1 - Build
FROM node:22-alpine AS build
WORKDIR /app
COPY package*.json .
RUN npm install
COPY . .
RUN npm run build

# Étape 2 - Run
FROM nginx:alpine
COPY --from=build /app/dist/memoriae-front/browser /usr/share/nginx/html
# COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
