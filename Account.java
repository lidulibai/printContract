package Accounts;

import AMounts.Amount;
import Boot.boot;
import Configure.Config;
import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;
import org.msgpack.unpacker.Unpacker;
import org.rocksdb.RocksDBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Account
{
    private final byte[] address;
    private boolean locked = true;

    private MessagePack mp;

    public Account(byte[] address) {
        this.address = address;
    }

    protected static byte[] getKey(byte[] address) {
        return address;
    }

    public static Account getAccount(byte[] address) {
        byte[] encode = getKey(address);
        return new Account(address);
    }

    public void adjustAvailable(byte[] address) {
        Account acc = getAccount(address);
    }

    public byte[] getAddress() {
        return address;
    }

    public Amount getAvailable() {
        return available;
    }
    public boolean getLocked() {
        return locked;
    }

    private void setLocked(boolean locked) {
        this.locked = locked;
    }
}