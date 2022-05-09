FROM node:14.17.6 AS build
WORKDIR '/src/main/TrelloWebApp'
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx
COPY default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /src/app/dist/trellowebapp /usr/share/nginx/html
EXPOSE 80
