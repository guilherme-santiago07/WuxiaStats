package me.dev.zennyel.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

public enum SkullAPI {
    a(getHeadUrl("1a74d9212e6491bc370caf025fd459fe632ca7c6ba4df7ebefafd489ab32fd")),
    b(getHeadUrl("a01783f37c7b5f691c615130ab85e1e1ad25dffceafd7bb178b92173df2aaa")),
    c(getHeadUrl("89b66dbf01c94aef7dca1abe8536c9e512969fa32f5707542979caf7258adc5")),
    d(getHeadUrl("1866b462b776374328d8daf2ce9e5abc3ecc64481d54a69cd4c2b25e7630c6af")),
    e(getHeadUrl("5223afe895d3d4e32f393dd301989a317585d9ebe3f5d76d4380f5a06f95")),
    f(getHeadUrl("fceb2a4441b73d4e744aeecba9fe6afb18470b99a262bd1ba1bcc6913a2baaf")),
    g(getHeadUrl("bd62d63cfb4b8731fcd2635dcf69203df393946e1aeca2605ab963e2829dab")),
    h(getHeadUrl("8f5b871ae47eba146b5b4cf89fc20dc3236952a7ea867898bec1f3b719712a")),
    i(getHeadUrl("e7a2f8bf7e138d6ba9246f7dc1ff144f43ed512e44892b1e316535ea882f7a5")),
    j(getHeadUrl("1df8b482ee76979fdb9467c8e666cc6aec5cb44859bb74579f016291ed01175")),
    k(getHeadUrl("5198fce7eafdaca15bd6e98ca94b4046583a8b2e5411cc8c944588fd7f1a5f97")),
    l(getHeadUrl("9f78466a7b5aae90884c6e2227d524978136354457382d3647b07d096c2a22e")),
    m(getHeadUrl("f2f2d827495053557c52e55ccdd571bd33184fc14928441f3a97eab38922812c")),
    n(getHeadUrl("651a7ee66f60237419917fdc75f5d341cfbb9f7ccaaef3aff2af419d588e701a")),
    o(getHeadUrl("d97aa8b9f7ed8c38ae41f1bd6d9e10eb57bc87e18cac247e7bd2a4e0e588578a")),
    p(getHeadUrl("72747139f338a46a45903b7633f270502d64f066515f4d2f9def80cff91150ff")),
    q(getHeadUrl("e0fcfc742ceb9464efc971a31c5896d971e4772772745756225b0643e4e93")),
    r(getHeadUrl("d82e6f0f9eaef56f383a4c5a392b194bf1a1b63559ac36255ccef2463c38c")),
    s(getHeadUrl("bcef333aaba242c6f28f49e96689ab4bf2bc126bbc298555b93da79328419d8")),
    t(getHeadUrl("e5bf4db51fab4aa279628dfb2a1720e01bf7e9b94bb8c7e702fb63757405d")),
    u(getHeadUrl("3ade4661e998aeb20136396d94136c27d49902280ab7eecfa887db48a9b6cf")),
    v(getHeadUrl("9df53a18c59683fd63f6822cd4464d12c862d7818c68b2302857b719f55e4fe9")),
    w(getHeadUrl("357b1f6eb7cf0fd20499a5be6b82b25cf5cbd8e57daac7351c85c45fb5fe5c")),
    x(getHeadUrl("bcc5fae3b650e6edb3245fa41e5be2da79b0e17f22c4b4e1e59b32f573202d18")),
    y(getHeadUrl("367477f9cb5f036438ce98ce697b91358cb97641c5d153a7e3888dae32e215")),
    z(getHeadUrl("cfa730b5ec73ceaf4aad524e207ff31df785e7ab6db1aeb3f1aa4d245ed2e"));

    final ItemStack letra;

    SkullAPI(ItemStack letra) {
        this.letra = letra;
    }

    public static ItemStack of(String s) {
        return valueOf(String.valueOf(ChatColor.stripColor(s).trim().charAt(0)).toLowerCase()).letra;
    }

    private static ItemStack getHeadUrl(String url) {
        String url2 = "https://textures.minecraft.net/texture/"+url;
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        if (url2 != null && !url2.isEmpty()) {
            SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
            GameProfile profile = new GameProfile(UUID.randomUUID(), (String)null);
            String format = String.format("{textures:{SKIN:{url:\"%s\"}}}", url2);
            String encodedData = Base64.getEncoder().encodeToString(format.getBytes());
            profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
            Field profileField = null;

            try {
                profileField = skullMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(skullMeta, profile);
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException var8) {
                var8.printStackTrace();
            }

            skull.setItemMeta(skullMeta);
            return skull;
        } else {
            return skull;
        }
    }
}