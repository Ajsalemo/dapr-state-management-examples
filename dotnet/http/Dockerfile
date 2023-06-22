FROM mcr.microsoft.com/dotnet/aspnet:7.0 AS base
WORKDIR /app
EXPOSE 8080

FROM mcr.microsoft.com/dotnet/sdk:7.0 AS build
WORKDIR /src
COPY ["http.csproj", "."]
RUN dotnet restore "http.csproj"
COPY . .
WORKDIR "/src/"
RUN dotnet build "http.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "http.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "http.dll"]