<template>
  <div class="container">
    <section id="presentation" class="text-center">
      <h1>Social Commands</h1>

      <div style="height: 0.75rem;"></div>

      <p>A Spigot Plugin that adds commands like <code>/discord</code> to your server</p>

      <div style="height: 2rem;"></div>

      <div class="buttons">
        <a href="https://www.spigotmc.org/resources/socialcommands.95783/" class="button">Download</a>
        <a href="https://nethermc.gitbook.io/socialcommands/" class="button">Wiki</a>
      </div>
    </section>
  </div>
</template>

<style lang="scss" scoped>
.container {
  margin-top: 4rem;
}

#presentation {
  h1 {
    font-size: 2.1rem;
  }

  p {
    font-size: 1.3rem;
  }

  .buttons {
    .button {
      font-size: 1.2rem;
      text-transform: uppercase;
      font-weight: bolder;
      letter-spacing: 1px;
      display: inline-block;

      &:not(:first-of-type) {
        margin-left: 1rem;
      }
    }
  }
}
</style>
