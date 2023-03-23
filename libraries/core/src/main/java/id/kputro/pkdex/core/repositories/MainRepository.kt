package id.kputro.pkdex.core.repositories

import id.kputro.pkdex.core.network.clients.main.MainClient

class MainRepository(
  val client: MainClient,
  val mockStorage: MockStorage,
) {
  // Not used at the moment..
}