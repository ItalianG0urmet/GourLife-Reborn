
# Glife Plugin ![](https://img.shields.io/badge/Platform-Linux-only-blue) ![Alpha](https://img.shields.io/badge/Status-Release-green)

![Banner](https://images-ext-2.discordapp.net/external/K_Vj-_8FzSL7B57Ycey0crHLgwftXVdKUHPVUceQ26c/https/i.postimg.cc/yxBjZCXB/Glifebanner-dsgvgd.jpg?format=webp&width=2560&height=1180)

## **Description:**

This Spigot plugin enhances gameplay by introducing a life system. Each player starts with three lives, loses one upon death, and gains one when killing another player. The plugin includes customizable placeholders and configurations.

**Note:** When a player's life count reaches zero, you can set in the configuration file a command.

## **Commands:**

1. `/glife check <player>` - View a player's lives.
2. `/glife set <player> <number>` - Set a player's lives.
3. `/glife reload` - View a player's lives.

## **Configurations:**
Config.yml
```yaml
database:
  host: "localhost"
  port: 3306
  name: "database_name"
  user: "database_user"
  password: "password"

settings:
  Join-messages: true
  leave-messages: true
  death-message: true
  final-message: true
  life-number: 3
```
Language.yml
```
messages:
  prefix: "<gray>[<white>ServerName<gray>]<dark_gray> » "
  no-player: "<red>Player not found"
  reset-life: "<yellow>%player% is back in the game"
  no-permission: "<red>You don't have the required permissions"
  check-lives: "<red>%player% has %lives% lives"
  your-lives: "<blue>You have %lives% lives"
  give-life: "<blue>You have donated 1 life"
  receive-life: "<blue>You have received 1 life from %player%"
  not-enough-lives: "<red>You don't have enough lives"
  cant-find-player: "<red>Player not found"
  cant-send-to-yourself: "<red>You can't do that to yourself"
  title_revelation: "<red>Revelation"
  subtitle_revelation: "<red>"
  death: "<yellow>%player% has died and now has <red>❤ %lives% lives"
  death-by-player: "<yellow>%player% had died by %killer% and now has <red>❤ %lives% lives"
  final-death: "<yellow>%player% has died permanently"
  join: "<yellow>%player% has joined the server!"
  leave: "<red>%player% has left the server"
  gained-life: "<blue>You have gained 1 life"
  set-life: "<blue>New life count: %new_life%"

  # Help
  help-commands: "<red>\n Usage: \n give: /life give <player> \n check: /life check <player>"

```

## **Placeholders:**

- Life Placeholder: `%glife_life%`

## **Permissions:**

- `glife.admin`

---
