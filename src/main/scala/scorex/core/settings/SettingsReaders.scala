package scorex.core.settings

import java.io.File
import java.net.InetSocketAddress

import com.typesafe.config.Config
import net.ceedubs.ficus.readers.ValueReader
import scorex.core.utils.ByteStr

trait SettingsReaders {
  implicit val byteStrReader: ValueReader[ByteStr] = (cfg, path) => ByteStr.decodeBase58(cfg.getString(path)).get
  implicit val fileReader: ValueReader[File] = (cfg, path) => new File(cfg.getString(path))
  implicit val byteValueReader: ValueReader[Byte] = (cfg, path) => cfg.getInt(path).toByte
  implicit val inetSocketAddressReader: ValueReader[InetSocketAddress] = { (config: Config, path: String) =>
    val split = config.getString(path).split(":")
    new InetSocketAddress(split(0), split(1).toInt)
  }
}