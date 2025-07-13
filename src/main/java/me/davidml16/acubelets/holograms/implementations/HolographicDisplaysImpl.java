package me.davidml16.acubelets.holograms.implementations;

import me.davidml16.acubelets.Main;
import me.davidml16.acubelets.enums.CubeletBoxState;
import me.davidml16.acubelets.holograms.HologramHandler;
import me.davidml16.acubelets.holograms.HologramImplementation;
import me.davidml16.acubelets.objects.CubeletMachine;
import me.davidml16.acubelets.objects.rewards.Reward;
import me.davidml16.acubelets.utils.RepeatingTask;
import me.davidml16.acubelets.utils.Utils;
import me.filoghost.holographicdisplays.api.HolographicDisplaysAPI;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import me.filoghost.holographicdisplays.api.hologram.VisibilitySettings;
import me.filoghost.holographicdisplays.api.hologram.line.TextHologramLine;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class HolographicDisplaysImpl implements HologramImplementation, Listener {

    private static final double LINE_HEIGHT = 0.36;
    private static final double LINE_HEIGHT_REWARD = 0.32;
    private final Main main;
    private final HologramHandler hologramHandler;
    private final HashMap<CubeletMachine, HashMap<UUID, Hologram>> holograms;
    String debug = "%%__USER__%%";

    public HolographicDisplaysImpl(Main main, HologramHandler hologramHandler) {
        this.main = main;
        this.hologramHandler = hologramHandler;
        this.holograms = new HashMap<>();
    }

    @Override
    public void loadHolograms() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            loadHolograms(p);
        }
    }

    @Override
    public void loadHolograms(Player p) {
        for (CubeletMachine box : main.getCubeletBoxHandler().getMachines().values()) {
            loadHolograms(p, box);
        }
    }

    @Override
    public void loadHolograms(CubeletMachine box) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            loadHolograms(p, box);
        }
    }

    @Override
    public void removeHolograms() {
        for (CubeletMachine machine : holograms.keySet()) {
            for (Hologram holo : holograms.get(machine).values()) {
                holo.delete();
            }
        }
    }

    @Override
    public void reloadHolograms() {
        hologramHandler.setActualColor(hologramHandler.getColorAnimation().nextColor());

        for (Player p : Bukkit.getOnlinePlayers()) {
            reloadHolograms(p);
        }
    }

    @Override
    public void loadHolograms(Player p, CubeletMachine box) {
        HolographicDisplaysAPI api = HolographicDisplaysAPI.get(main);
        Hologram hologram = api.createHologram(box.getLocation()
            .clone()
            .add(0.5, 1.025 + (box.getBlockHeight() + 0.1875), 0.5));
        VisibilitySettings visibilitySettings = hologram.getVisibilitySettings();

        visibilitySettings.setGlobalVisibility(VisibilitySettings.Visibility.HIDDEN);
        visibilitySettings.setIndividualVisibility(p, VisibilitySettings.Visibility.VISIBLE);

        if (box.getState() == CubeletBoxState.EMPTY) {
            List<String> lines = hologramHandler.getLines(p);
            int max = Math.max(main.getLanguageHandler()
                .getMessageList("Holograms.CubeletAvailable")
                .size(), main.getLanguageHandler().getMessageList("Holograms.NoCubeletAvailable").size());

            for (String line : lines) {
                hologram.getLines().appendText(line);
            }

            hologram.setPosition(box.getLocation()
                .clone()
                .add(0.5, (max * LINE_HEIGHT) + (box.getBlockHeight() + 0.1875), 0.5));
        } else if (box.getState() == CubeletBoxState.REWARD) {
            List<String> lines = hologramHandler.getLinesReward(p, box.getPlayerOpening(), box.getLastReward());

            hologram.setPosition(box.getLocation()
                .clone()
                .add(0.5, (lines.size() * LINE_HEIGHT_REWARD) + (box.getBlockHeight() + 0.1875), 0.5));

            for (String line : lines) {
                if (!line.contains("%reward_icon%")) hologram.getLines().appendText(line);
                else hologram.getLines().appendItem(box.getLastReward().getIcon());
            }
        }

        if (holograms.get(box) == null) holograms.put(box, new HashMap<>());

        holograms.get(box).put(p.getUniqueId(), hologram);
    }

    @Override
    public void reloadHolograms(Player p) {
        for (CubeletMachine box : main.getCubeletBoxHandler().getMachines().values()) {
            reloadHologram(p, box);
        }
    }

    @Override
    public void reloadHologram(CubeletMachine box) {
        for (Hologram hologram : holograms.get(box).values()) {
            hologram.getLines().clear();
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            reloadHologram(p, box);
        }
    }

    @Override
    public void reloadHologram(Player p, CubeletMachine box) {
        if (holograms.get(box) == null || !holograms.get(box).containsKey(p.getUniqueId())) return;

        Hologram hologram = holograms.get(box).get(p.getUniqueId());

        if (!Objects.equals(box.getLocation().getWorld(), p.getLocation().getWorld()) || box.getLocation()
            .distanceSquared(p.getLocation()) > hologramHandler.getVisibilityDistance()) {
            VisibilitySettings visibilitySettings = hologram.getVisibilitySettings();
            if (visibilitySettings.isVisibleTo(p))
                visibilitySettings.setIndividualVisibility(p, VisibilitySettings.Visibility.HIDDEN);
            return;
        }

        if (box.getState() == CubeletBoxState.EMPTY) {
            List<String> lines = hologramHandler.getLines(p);

            VisibilitySettings visibilitySettings = hologram.getVisibilitySettings();
            if (!visibilitySettings.isVisibleTo(p))
                visibilitySettings.setIndividualVisibility(p, VisibilitySettings.Visibility.VISIBLE);

            int max = Math.max(main.getLanguageHandler()
                .getMessageList("Holograms.CubeletAvailable")
                .size(), main.getLanguageHandler().getMessageList("Holograms.NoCubeletAvailable").size());
            if (hologram.getLines().size() != lines.size()) hologram.setPosition(box.getLocation()
                .clone()
                .add(0.5, (max * LINE_HEIGHT) + (box.getBlockHeight() + 0.1875), 0.5));

            if (hologram.getLines().size() > lines.size()) {
                for (int i = lines.size(); i < hologram.getLines().size(); i++)
                    hologram.getLines().remove(i);

            } else if (hologram.getLines().size() < lines.size()) {
                for (int i = hologram.getLines().size(); i < lines.size(); i++)
                    hologram.getLines().appendText(lines.get(i));
            }

            for (int i = 0; i < lines.size(); i++) {
                TextHologramLine line = (TextHologramLine) hologram.getLines().get(i);
                if (!(line.getText().equalsIgnoreCase(lines.get(i)))) line.setText(lines.get(i));
            }
        }
    }

    @Override
    public void clearLines(CubeletMachine box) {
        for (Hologram hologram : holograms.get(box).values()) {
            hologram.getLines().clear();
        }
    }

    @Override
    public void clearHolograms(CubeletMachine box) {
        holograms.get(box).clear();
    }

    public void moveHologram(CubeletMachine box) {
        int max = Math.max(main.getLanguageHandler()
            .getMessageList("Holograms.CubeletAvailable")
            .size(), main.getLanguageHandler().getMessageList("Holograms.NoCubeletAvailable").size());

        if (holograms.get(box) == null) return;

        for (Hologram hologram : holograms.get(box).values()) {
            hologram.setPosition(box.getLocation()
                .clone()
                .add(0.5, (max * LINE_HEIGHT) + (box.getBlockHeight() + 0.1875), 0.5));
        }
    }

    public void rewardHologram(CubeletMachine box, Reward reward) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (holograms.get(box) == null) continue;

            if (holograms.get(box).containsKey(p.getUniqueId())) {
                Hologram hologram = holograms.get(box).get(p.getUniqueId());
                hologram.getLines().clear();

                List<String> lines = hologramHandler.getLinesReward(p, box.getPlayerOpening(), reward);

                hologram.setPosition(box.getLocation()
                    .clone()
                    .add(0.5, (lines.size() * LINE_HEIGHT_REWARD) + (box.getBlockHeight() + 0.1875), 0.5));

                for (String line : lines) {
                    if (!line.contains("%reward_icon%")) hologram.getLines().appendText(line);
                    else hologram.getLines().appendItem(reward.getIcon());
                }
            }
        }
    }

    @Override
    public void showTextAndIcon(CubeletMachine box, List<String> lines, ItemStack item) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (holograms.get(box) == null) continue;

            if (holograms.get(box).containsKey(p.getUniqueId())) {
                Hologram hologram = holograms.get(box).get(p.getUniqueId());
                hologram.getLines().clear();

                hologram.setPosition(box.getLocation()
                    .clone()
                    .add(0.5, (lines.size() * LINE_HEIGHT_REWARD) + (box.getBlockHeight() + 0.1875), 0.5));

                for (String line : lines) {
                    if (!line.contains("%reward_icon%")) hologram.getLines().appendText(Utils.translate(line));
                    else hologram.getLines().appendItem(item);
                }
            }
        }
    }

    @Override
    public void moveHologramShowTextAndIcon(CubeletMachine box, List<String> lines) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (holograms.get(box) == null) continue;

            if (holograms.get(box).containsKey(p.getUniqueId())) {
                Hologram hologram = holograms.get(box).get(p.getUniqueId());
                hologram.getLines().clear();

                hologram.setPosition(box.getLocation()
                    .clone()
                    .add(0.5, (lines.size() * LINE_HEIGHT_REWARD) + (box.getBlockHeight() + 0.1875), 0.5));
            }
        }
    }

    @Override
    public RepeatingTask duplicationRewardHologram(CubeletMachine box, Reward reward) {
        int duplicationPoints = box.getLastDuplicationPoints();

        return new RepeatingTask(main, 0, 1) {
            int pointsPerTick = duplicationPoints > 40 ? duplicationPoints / 40 : duplicationPoints > 20 ? duplicationPoints / 20 : 1;
            int pointsToShow = 0;

            @Override
            public void run() {
                if (pointsPerTick == 0) {
                    pointsPerTick = box.getLastDuplicationPoints() / 40;
                }

                List<String> lines = hologramHandler.getLinesRewardDuplicated(reward, pointsToShow);

                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!main.isSetting("HDVisibleToAllPlayers"))
                        if (!box.getPlayerOpening().getUuid().equals(p.getUniqueId())) continue;

                    if (holograms.get(box) == null) continue;

                    if (holograms.get(box).containsKey(p.getUniqueId())) {
                        Hologram hologram = holograms.get(box).get(p.getUniqueId());
                        hologram.setPosition(box.getLocation()
                            .clone()
                            .add(0.5, (lines.size() * LINE_HEIGHT_REWARD) + (box.getBlockHeight() + 0.1875), 0.5));

                        for (int i = 0; i < lines.size(); i++) {
                            if (!lines.get(i).equalsIgnoreCase("%reward_icon%")) {
                                ((TextHologramLine) hologram.getLines().get(i)).setText(lines.get(i));
                            }
                        }
                    }
                }

                if ((pointsToShow + pointsPerTick) <= duplicationPoints) pointsToShow += pointsPerTick;
                else pointsToShow = duplicationPoints;
            }
        };
    }

    @Override
    public void removeHolograms(Player p) {
        for (CubeletMachine box : main.getCubeletBoxHandler().getMachines().values()) {
            if (holograms.get(box) == null) continue;

            if (holograms.get(box).containsKey(p.getUniqueId())) {
                if (holograms.get(box).get(p.getUniqueId()) == null) continue;

                holograms.get(box).get(p.getUniqueId()).delete();

                holograms.get(box).remove(p.getUniqueId());
            }
        }
    }

    @Override
    public void removeHolograms(CubeletMachine box) {
        if (holograms.get(box) == null) return;

        for (Hologram holo : holograms.get(box).values()) {
            holo.delete();
        }
    }

}
